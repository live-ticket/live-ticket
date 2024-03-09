package com.ll.ticket.domain.customer.question.service;

import com.ll.ticket.domain.customer.question.dto.QuestionResponse;
import com.ll.ticket.domain.customer.question.dto.UpdateRequest;
import com.ll.ticket.domain.customer.question.dto.WriteRequest;
import com.ll.ticket.domain.customer.question.entity.Question;
import com.ll.ticket.domain.customer.question.repository.QuestionRepository;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@ToString
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberService memberService;

    /**
     * 글작성
     * 현재 로그인한 계정을 찾아 저장 한다
     */
    @Transactional
    public Long createQuestion(WriteRequest writeRequest, MultipartFile multipartFile) {

        //  현재 로그인한 사용자의 정보를 가져온다. , 작성자를 포함 하기 위함
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 현재 로그인한 사용자의 이메일
        String authorEmail = authentication.getName();

        Member member = memberService.findByEmail(authorEmail)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        writeRequest.setMember(member);

        try {
            // 이미지 저장 경로
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            UUID uuid = UUID.randomUUID(); //파일 랜덤 고유 식별자

            String fileName = uuid + "_" + multipartFile.getOriginalFilename();

            File saveFile = new File(projectPath, fileName);

            multipartFile.transferTo(saveFile);
            //경로를 저장
            writeRequest.setFileName(fileName);

            writeRequest.setImagePath("/files/" + fileName);

            return questionRepository.save(writeRequest.toEntity()).getCustomerQId();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("파일 전송 중 오류가 발생했습니다.");
        }
    }

    //질문 찾는다
    @Transactional
    public QuestionResponse findQuestion(Long id) {

        Question question = questionRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("게시글을 찾 을 수 없습니다 "));

        return new QuestionResponse(question); //DTO 반환
    }

    @Transactional
    public void updateQuestion(Long id, UpdateRequest updateRequest) {

        Question question = questionRepository.findById(id).orElseThrow(() ->

                new RuntimeException("게시글을 찾을수 없습니다"));

        question.changeQuestion(updateRequest.getQuestionTitle(), updateRequest.getQuestionContent(), updateRequest.getQuestionCategory(),
                updateRequest.getImagePath(), updateRequest.getFileName());

        this.questionRepository.save(question);
    }

    @Transactional
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    /**
     *  문의 내역
     *  관리자 로 로그인 하면 모든 문의글을 볼수 있고
     *  사용자로 로그인 하면 내가 작성한 문의 글만 볼수있다.
     */
    public List<QuestionResponse> getQuestion(String email ) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(a ->
                a.getAuthority().equals("ADMIN"))) {

            return questionRepository.findAll().stream()
                    .map(question -> new QuestionResponse(question))
                    .toList();
        }

        return questionRepository.findByMemberEmail(email).stream()
                .map(question -> new QuestionResponse(question))
                .toList();
    }

}




