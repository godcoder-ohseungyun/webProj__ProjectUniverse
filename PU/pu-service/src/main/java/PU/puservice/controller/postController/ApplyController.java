package PU.puservice.controller.postController;

import PU.puservice.domain.member.Member;
import PU.puservice.domain.post.MasterKey;
import PU.puservice.domain.post.Post;
import PU.puservice.service.memberService.MemberService;
import PU.puservice.service.postService.PostService;
import PU.puservice.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@Slf4j
@RequestMapping("/post")
public class ApplyController {

    private PostService postService;
    private MemberService memberService;

    @Autowired
    public ApplyController(PostService postService,MemberService memberService) {
        this.postService = postService;
        this.memberService = memberService;
    }

    /**
     * 지원하기 클릭시
     * 작성자 게시물내 지원자 목록에 지원자들 프로필 링크 나와야함
     *
     */

    @PostMapping("/{postId}") //포스트 맞는지 검토할것
    @ResponseBody
    public Object postDetail(@PathVariable Long postId, HttpServletRequest request) {
        Post foundPost = postService.findPostById(postId);

        /**
         * 세션에는 맴버 객체가 저장되어있다.
         */
        HttpSession session = request.getSession(false);

        //새션이 존재하면
        if (session != null) {
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

            foundPost.getVolunteers().add(loginMember.getLoginId());  //로그인한사람 == 지원자
            //지원자 닉네임(로그인 아이디) 추가
        }
        return "";
    }

}

