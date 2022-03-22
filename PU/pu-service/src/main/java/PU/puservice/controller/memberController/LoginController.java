package PU.puservice.controller.memberController;

import PU.puservice.domain.loginForm.LoginForm;
import PU.puservice.domain.member.Member;
import PU.puservice.exception.AccessDeniedException;
import PU.puservice.service.loginService.MemberLoginService;
import PU.puservice.session.SessionConst;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * @Author 오승윤
 * 로그인 컨트롤러
 * 1.로그인 및 로그아웃
 *
 * 로그인: /login
 * 로그아웃: /logout
 */

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberLoginService memberLoginService;



    @PostMapping("/login")
    public void login(@RequestBody LoginForm form, HttpServletRequest request, HttpServletResponse response){
        
        Member loginMember  = memberLoginService.login(form.getLoginId(),form.getPassword());

        if(loginMember == null){
            throw new AccessDeniedException("login information is invalid.", HttpStatus.UNAUTHORIZED);
        }


        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성: true
        HttpSession session = request.getSession(true);
        //세션 헤더에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember); //bound name , value in bound

        //헤더 로케이션에 최근 요청 uri 담아서 반환 : 다시 돌아가게 redirect 권장
        //TODO: 아래 구문 잘못되었음 수정해야함
        response.addHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath().path("/{loginId}").buildAndExpand(loginMember.getLoginId()).toUriString());
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT) //상태코드 부여
    @PostMapping("/logout")
    public void logout(HttpServletRequest request , HttpServletResponse response) {


        //세션을 삭제한다.
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성안함: false
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); //세션 제거
        }

        response.addHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath().toUriString());
    }

}
