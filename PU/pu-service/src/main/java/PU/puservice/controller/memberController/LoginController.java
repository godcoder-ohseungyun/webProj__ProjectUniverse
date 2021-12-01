package PU.puservice.controller.memberController;

import PU.puservice.domain.member.LoginForm;
import PU.puservice.domain.member.Member;
import PU.puservice.service.loginService.MemberLoginService;
import PU.puservice.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final MemberLoginService memberLoginService;

    @GetMapping
    public String loginForm(@ModelAttribute LoginForm form){
        return "login/loginForm";
    }

    @PostMapping
    public String login(@ModelAttribute LoginForm form, HttpServletRequest request){
        Member loginMember  = memberLoginService.login(form.getLoginId(),form.getPassword());

        if(loginMember.equals(null)){
            return "login/loginForm";
        }

        /**
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);
         쿠키 -> 세션으로 대체
        */
        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성: true
        HttpSession session = request.getSession(true);
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember); //bound name , value in bound


        return "redirect:/basic/home";
    }


    @PostMapping //WHY LOGOUT IS POST?
    public String logout(HttpServletRequest request) {
        /**
        expireCookie(response, "memberId"); //쿠키 폐기
         쿠키->세션으로 대체
        */
        //세션을 삭제한다.
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성안함: false
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); //세션 제거
        }
         return "";
    }
    
//    private void expireCookie(HttpServletResponse response, String cookieName) {
//        Cookie cookie = new Cookie(cookieName, null);
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//    }

}
