package com.ll.ticket.domain.order.controller;

import com.ll.ticket.domain.concert.entity.Concert;
import com.ll.ticket.domain.concert.entity.ConcertDate;
import com.ll.ticket.domain.concert.service.ConcertService;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.service.MemberService;
import com.ll.ticket.domain.order.dto.OrderPayInfoDto;
import com.ll.ticket.domain.order.entity.Order;
import com.ll.ticket.domain.order.service.OrderService;
import com.ll.ticket.domain.recaptcha.service.RecaptchaService;
import com.ll.ticket.global.app.AppConfig;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Base64;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final ConcertService concertService;
    private final MemberService memberService;
    private final RecaptchaService recaptchaService;

    @GetMapping("/{id}")
    public String showOrder(@PathVariable("id") long id, Principal principal, Model model) {
        Order order = orderService.findById(id).orElse(null);

        if (order == null) {
            throw new IllegalArgumentException("존재하지 않는 주문입니다.");
        }

        if (principal == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        Member member = memberService.getMember(principal.getName());

        if (!orderService.checkOrderAccess(member, order)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        model.addAttribute("order", order);

        return "domain/order/order";
    }

    @PostMapping("/{concertId}")
    public String order(@PathVariable("concertId") Long concertId, @RequestParam("concertDateId") String concertDateId, @RequestParam("selectedSeatsData") String selectedSeatsData, Principal principal) {
        try {
            if (principal == null) {
                throw new IllegalArgumentException("로그인이 필요합니다.");
            }

            Concert concert = concertService.findById(concertId);
            Member member = memberService.getMember(principal.getName());

            ConcertDate concertDate = concertService.findConcertDateById(concertDateId).orElse(null);

            if (concertDate == null) {
                throw new IllegalArgumentException("공연 날짜가 존재하지 않습니다.");
            }

            Order order = orderService.order(concert, concertDate, member, selectedSeatsData);
            return "redirect:/order/" + order.getOrderId();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "redirect:/concert/" + concertId;
        }

    }

    @ResponseBody
    @PostMapping("/{id}/pay")
    public void saveOrderUserInfo(@PathVariable("id") long id, @RequestBody OrderPayInfoDto orderPayInfoDto, Principal principal) {
        if (!recaptchaService.verifyRecaptcha(orderPayInfoDto.getRecaptcha())) {
            throw new IllegalArgumentException("reCAPTCHA를 확인해주세요.");
        }

        Order order = orderService.findById(id).orElse(null);

        if (order == null) {
            throw new IllegalArgumentException("존재하지 않는 주문입니다.");
        }

        if (principal == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        Member member = memberService.getMember(principal.getName());

        if (!orderService.checkOrderAccess(member, order)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        orderService.saveOrderPayInfo(order, orderPayInfoDto);
    }

    @GetMapping("/success")
    public String showSuccess() {
        return "domain/order/success";
    }

    @GetMapping("/fail")
    public String showFail(String failCode, String failMessage, HttpServletRequest request) {
        request.setAttribute("code", failCode);
        request.setAttribute("message", failMessage);

        return "domain/order/fail";
    }

    @PostMapping("/confirm")
    public ResponseEntity<JSONObject> confirmPayment2(@RequestBody String jsonBody) throws Exception {

        JSONParser parser = new JSONParser();
        String orderId;
        String amount;
        String paymentKey;
        try {
            // 클라이언트에서 받은 JSON 요청 바디입니다.
            JSONObject requestData = (JSONObject) parser.parse(jsonBody);
            paymentKey = (String) requestData.get("paymentKey");
            orderId = (String) requestData.get("orderId");
            amount = (String) requestData.get("amount");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // 체크
        orderService.checkCanPay(orderId, Long.parseLong(amount));

        JSONObject obj = new JSONObject();
        obj.put("orderId", orderId);
        obj.put("amount", amount);
        obj.put("paymentKey", paymentKey);

        String apiKey = AppConfig.getTossPaymentsSecretKey();

        // 토스페이먼츠 API는 시크릿 키를 사용자 ID로 사용하고, 비밀번호는 사용하지 않습니다.
        // 비밀번호가 없다는 것을 알리기 위해 시크릿 키 뒤에 콜론을 추가합니다.
        // @docs https://docs.tosspayments.com/reference/using-api/authorization#%EC%9D%B8%EC%A6%9D
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((apiKey + ":").getBytes("UTF-8"));
        String authorizations = "Basic " + new String(encodedBytes, 0, encodedBytes.length);

        // 결제 승인 API를 호출하세요.
        // 결제를 승인하면 결제수단에서 금액이 차감돼요.
        // @docs https://docs.tosspayments.com/guides/payment-widget/integration#3-결제-승인하기
        URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200 ? true : false;

        // 결제 승인이 완료
        if (isSuccess) {
            orderService.payByTossPayments(orderService.findByCode(orderId).get(), Long.parseLong(amount));
        } else {
            throw new RuntimeException("결제 승인 실패");
        }

        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

        // TODO: 결제 성공 및 실패 비즈니스 로직을 구현하세요.
        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        responseStream.close();

        return ResponseEntity.status(code).body(jsonObject);
    }
}
