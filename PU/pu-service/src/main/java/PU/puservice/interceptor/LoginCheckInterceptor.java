package PU.puservice.interceptor;

import PU.puservice.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 로그인 인증 인터셉터 HandlerInterceptor를 구현
 * preHandle: 컨트롤러 호출전에 실행되는 메서드
 * 로그인 인증은 컨트롤러 호출전에 실행되어야 함으로 preHandle만 정의한다.
 */
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    //컨트롤러 호출 전에 호출된다.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행 {}", requestURI);

        //request http  session 확인
        HttpSession session = request.getSession(false);
        //session이 없거나 LOGIN_MEMBER에 대한 세션이 없으면
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {

            log.info("미인증 사용자 요청");

            //로그인으로 redirect
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }
        return true;
    }
}
