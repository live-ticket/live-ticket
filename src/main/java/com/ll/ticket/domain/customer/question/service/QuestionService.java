package com.ll.ticket.domain.customer.question.service;

import com.ll.ticket.domain.customer.question.dto.WriteRequest;
import com.ll.ticket.domain.customer.question.repository.QuestionRepository;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.repository.MemberRepository;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@ToString
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    /**
     * 아이디 Question Entity id 값 반환
     */
   @Transactional
    public Long createQuestion(WriteRequest writeRequest , MultipartFile multipartFile ) {

       //  현재 로그인한 사용자의 정보를 가져온다. , 작성자를 포함 하기 위함
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

       // 현재 로그인한 사용자의 이메일
       String authorEmail = authentication.getName();

       Member member = memberRepository.findByEmail(authorEmail)
               .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

       writeRequest.setMember(member);

       try
    {
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
}




