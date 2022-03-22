package PU.puservice.controller.homeController;

import PU.puservice.domain.apply.MsgForm;
import PU.puservice.domain.member.Member;
import PU.puservice.exception.AccessDeniedException;
import PU.puservice.service.memberService.MemberService;
import PU.puservice.session.SessionConst;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;


@RestController
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;




    @GetMapping
    public Member accessHomePage(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);

        //세션이 없으면 home
        if (session == null) {
            throw new AccessDeniedException("Block access to users who are not logged in.",HttpStatus.UNAUTHORIZED);
        }

        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            throw new AccessDeniedException("login information is invalid.",HttpStatus.UNAUTHORIZED);
        }

        //세션이 유지되고 로그인 할수있는 회원임이 확인되면
        response.addHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{LoginId}")
                .buildAndExpand(loginMember.getLoginId())
                .toUriString());

        return loginMember;

    }


    @GetMapping("/msgs")
    public LinkedList<MsgForm> getMsgList(HttpServletRequest request){

        HttpSession session = request.getSession(false);

        //세션이 없으면 home
        if (session == null) {
            throw new AccessDeniedException("Block access to users who are not logged in.",HttpStatus.UNAUTHORIZED);
        }

        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            throw new AccessDeniedException("login information is invalid.",HttpStatus.UNAUTHORIZED);
        }

        return loginMember.getMsgs(); 
    }


}
