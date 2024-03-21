package com.ll.ticket.domain.customer.question.service;

import com.ll.ticket.domain.customer.question.component.FileStorageUtil;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
@ToString
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberService memberService;
    private final FileStorageUtil fileStorageUtil;

    @Value("${custom.file-path}")
    private String filePath; //yml 에 경로 저장 questionDetail.html 에 도따로 경로 지정해줘야함

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

        String fileName = fileStorageUtil.saveFile(multipartFile); //uuid + 파일 이름 반환

        writeRequest.setFileName(fileName);

        writeRequest.setImagePath(filePath + fileName);

        return questionRepository.save(writeRequest.toEntity()).getCustomerQId(); //질문 저장

    }

    //질문 찾는다
    public QuestionResponse findQuestion(Long id) {

        Question question = findById(id);

        return new QuestionResponse(question); //DTO 반환
    }

    public Question findById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("질문을 찾을 수 없습니다."));
    }


    /**
     * 질문 수정 , 파일 수정
     */
    @Transactional
    public void updateQuestion(Long id, UpdateRequest updateRequest ,MultipartFile multipartFile)  {

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다"));

        // 이전에 업로드된 파일을 삭제
        fileStorageUtil.deleteFile(question.getFileName());

        String fileName = fileStorageUtil.saveFile(multipartFile);

        updateRequest.setFileName(fileName);

        updateRequest.setImagePath(filePath + fileName);

        //파일 저장
        question.changeQuestion(updateRequest.getQuestionTitle(), updateRequest.getQuestionContent(), updateRequest.getQuestionCategory(),
                updateRequest.getImagePath(), updateRequest.getFileName());

        this.questionRepository.save(question);
    }
    /**
     * 질문 삭제 , 파일도 같이 삭제
     */
    @Transactional
    public void deleteQuestion(Long id) {

       Question question = questionRepository.findById(id).orElseThrow(()
               ->new IllegalArgumentException("게시글을 찾 을 수 없습니다 "));

        fileStorageUtil.deleteFile(question.getFileName()); //파일 삭제

        questionRepository.deleteById(id);
    }

    /**
     *  문의 내역
     *  관리자 로 로그인 하면 모든 문의글을 볼수 있고
     *  사용자로 로그인 하면 내가 작성한 문의 글만 볼수있다.
     */
    public Page<QuestionResponse> getQuestion(String email , int page) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); //로그인 사용자의 권한

        Pageable pageable = PageRequest.of(page -1, 10); //페이징

        if (auth.getAuthorities().stream().anyMatch(a ->
                a.getAuthority().equals("ADMIN"))) {

            Page<Question> pagingAdmin = questionRepository.findAll(pageable);
            return pagingAdmin.map(QuestionResponse::new);
        }

        Page<Question> pagingMember = questionRepository.findByMemberEmail(email, pageable);
        return pagingMember.map(QuestionResponse::new);
    }

}




