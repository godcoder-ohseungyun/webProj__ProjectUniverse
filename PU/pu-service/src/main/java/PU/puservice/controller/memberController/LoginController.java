package PU.puservice.controller.memberController;

import PU.puservice.domain.member.LoginForm;
import PU.puservice.domain.member.Member;
import PU.puservice.service.loginService.MemberLoginService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

        return "redirect:/basic/home";
    }


    @PostMapping //WHY LOGOUT IS POST?
    public String logout(HttpServletRequest request) {

         return "";
    }


}
