package PU.puservice.controller.homeController;

import PU.puservice.domain.member.Member;
import PU.puservice.exception.AccessDeniedException;
import PU.puservice.service.memberService.MemberService;
import PU.puservice.session.SessionConst;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URI;

/**
 * 응답 헤더 LOCATION에 URI 담아주기 : 이때 상태코드 301로 되어야함, 지금은 처리 안되어있어서 201 OK임
 * LOCATION header: 3xx (redirection) 또는 201(created) 상태 응답 과 함께 제공될 때만 의미를 제공합니다 .
 */
@RestController
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;


    @ApiOperation(value = "accessHomePage", notes = "로그인 여부에 따라 로그인 이후 메인페이지 접근을 결정합니다. \n - 비 로그인: 404 status  \n - 로그인: 로그인 회원에 대한 데이터 + ok status" )
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

        response.addHeader("Location", "Dddd");
        //세션이 유지되고 로그인 할수있는 회원임이 확인되면
        return loginMember; //로그인 멤버 데이터 함께반환

    }


}
