package PU.puservice.controller.memberController;

import PU.puservice.domain.member.LoginForm;
import PU.puservice.domain.member.Member;
import PU.puservice.service.loginService.MemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
    public String login(@ModelAttribute LoginForm form, HttpServletResponse response){
        Member loginMember  = memberLoginService.login(form.getLoginId(),form.getPassword());

        if(loginMember.equals(null)){
            return "login/loginForm";
        }

        //쿠키 생성 for 로그인 유지
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);

        return "redirect:/basic/home";
    }
}
