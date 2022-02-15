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


    @ApiOperation(value = "메인페이지 접근 처리", notes = "로그인 여부에 따라 `로그인 이후 메인페이지` 접근을 결정합니다." +
            " \n - `비 로그인` : 4xx 상태코드,에러 메세지 를 반환합니다.  " +
            "\n - `로그인` : ok 상태코드, 로그인 회원 객체 데이터 , header Location에 로그인 회원 프로필 uri 를 반환합니다." )

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


    @ApiOperation(value = "로그인한 회원 메세지 리스트 데이터를 반환", notes = "isRead 값에 true 읽음 false 읽지않음을 표기합니다.")
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
