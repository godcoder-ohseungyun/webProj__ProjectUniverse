package PU.puservice.controller.applyController;

import PU.puservice.domain.apply.ApproveForm;
import PU.puservice.domain.apply.MsgForm;
import PU.puservice.domain.member.Member;
import PU.puservice.domain.post.Post;
import PU.puservice.exception.AccessDeniedException;
import PU.puservice.exception.UserNotFoundException;
import PU.puservice.service.memberService.MemberService;
import PU.puservice.service.postService.PostService;
import PU.puservice.session.SessionConst;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.Optional;

/**
 * 프로젝트 지원 및 승인 절차를 다루는 컨트롤러 입니다.
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class ApplyController {

    private final PostService postService;
    private final MemberService memberService;


    @PostMapping("/apply")
    public void apply(@RequestParam("postId") Long postId, HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        //세션이 없으면 home
        if (session == null) {
            throw new AccessDeniedException("Block access to users who are not logged in.", HttpStatus.UNAUTHORIZED);
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            throw new AccessDeniedException("login information is invalid.", HttpStatus.UNAUTHORIZED);
        }

        Post findPost = postService.findPostById(postId);

        //해당 게시물 지원자 목록에 로그인한 회원 loginId 저장
        findPost.getVolunteers().add(loginMember.getLoginId());
    }


    @PostMapping("/approve")
    public void approve(@RequestBody ApproveForm approveForm) {
        
        Member findMember = memberService.findMemberByLoginId(approveForm.getLoginId()).orElseThrow(()->new UserNotFoundException("없는 회원 입니다.",HttpStatus.NOT_FOUND));
        Post findPost = postService.findPostById(approveForm.getPostId());
        
        //메시지 가공
        String createMsg = String.format("%s 님으로 부터의 메세지 , 공고제목: %s , 내용: %s"
                , findPost.getWriter(), findPost.getTitle() ,approveForm.getMsg());

        MsgForm msgForm = new MsgForm(false,createMsg);

        //지원자 프로필에 메세지 목록에 저장 / 제일 앞에 저장
        findMember.getMsgs().add(msgForm);
        
    }

    
}
