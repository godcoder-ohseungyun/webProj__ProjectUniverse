package PU.puservice.controller.homeController;

import PU.puservice.domain.member.Member;
import PU.puservice.service.memberService.MemberService;
import PU.puservice.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping
    public String accessHomePage(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);

        //세션이 없으면 home
        if (session == null) {
            return "main/home";
        }

        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "main/home";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "main/loginHome";

    }
}
