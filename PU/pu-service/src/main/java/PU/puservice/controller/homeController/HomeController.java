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


        //세션이 없으면 home
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "home";
        }
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }
        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";

        /**
         * CookieValue: request http header 쿠키조회

         if (memberId == null){
         return "basic/home"; //디렉토리 바꿔야됨 static home으로
         }
         //쿠키 null: 비로그인

         Member loginMember = memberService.findMemberByUniqueId(memberId);
         if(loginMember==null){
         return "basic/home";
         }
         //쿠키 have but wrong memberId

         model.addAttribute("member",loginMember);
         return ""; //별도의 로그인 전용 홈 화면 필요함
         // 로그아웃 버튼이나 로그인 이후 사용자만 쓰는 기능을 구현해야함
         */
    }
}
