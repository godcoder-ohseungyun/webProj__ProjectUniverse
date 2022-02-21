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


    @ApiOperation(value = "로그인 처리", notes = "로그인 처리를 담당합니다. \n - 입력한 로그인 정보가 잘못된경우 UNAUTHORIZED 상태 코드와 에러 메세지를 반환합니다." +
            "\n - 로그인에 성공한 경우 HTTP HEADER COOKIE에 세션 ID를 담아 반환합니다. " +
            "\n - 또한 HTTP HEADER Location에 redirect해야하는 uri를 반환합니다. 클라이언트는 반드시 해당 uri로 사용자를 redirect 시켜야 합니다.")
    @PostMapping("/login")
    public void login(@RequestBody LoginForm form, HttpServletRequest request, HttpServletResponse response){
        
        Member loginMember  = memberLoginService.login(form.getLoginId(),form.getPassword());

        if(loginMember.equals(null)){
            throw new AccessDeniedException("login information is invalid.", HttpStatus.UNAUTHORIZED);
        }


        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성: true
        HttpSession session = request.getSession(true);
        //세션 헤더에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember); //bound name , value in bound

        //헤더 로케이션에 최근 요청 uri 담아서 반환 : 다시 돌아가게 redirect 권장
        //TODO: 아래 구문 잘못되었음 수정해야함
        response.addHeader("Location", ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT) //상태코드 부여
    @ApiOperation(value = "로그아웃 처리", notes = "로그아웃을 담당합니다." +
            " \n - 세션을 폐기합니다.")
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {


        //세션을 삭제한다.
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성안함: false
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); //세션 제거
        }
        

    }

}
