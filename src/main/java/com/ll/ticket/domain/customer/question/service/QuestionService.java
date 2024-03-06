package com.ll.ticket.domain.customer.question.service;

import com.ll.ticket.domain.customer.question.dto.WriteRequest;
import com.ll.ticket.domain.customer.question.repository.QuestionRepository;
import com.ll.ticket.domain.member.entity.Member;
import com.ll.ticket.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
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
    public Long createQuestion(WriteRequest writeRequest , MultipartFile multipartFile , Member member) {
       try
    {
        // 이미지 저장 경로
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID(); //파일 랜덤 고유 식별자

        String fileName = uuid + "_" + multipartFile.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        multipartFile.transferTo(saveFile);
        //경로 저장
        writeRequest.setFileName(fileName);
        writeRequest.setImagePath("/files/" + fileName);

        //멤버 추가 하여 반환
        return questionRepository.save(writeRequest.toEntity(member)).getCustomerQId();

    } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException("파일 전송 중 오류가 발생했습니다.");
    }
   }
}




