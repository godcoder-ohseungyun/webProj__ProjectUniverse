package PU.puservice.filter;


import PU.puservice.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * 서블릿이 제공하는 Filter interface 구현
 * init(),destroy()는 Filter interface에 default method로 정의되어있다.
 *
 * doFilter(): 오버라이딩 해야한다.
 * 
 * 로그인 인증채크를 해주는 필터
 *
 */
@Slf4j
public class LoginCheckFilter implements Filter {

    /**
     * 화이트 리스트 CONST
     */
    private static final String[] whitelist = {"/", "/members/add", "/login",
            "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;//다운 캐스팅
        HttpServletResponse httpResponse = (HttpServletResponse) response; //다운 캐스팅
        
        String requestURI = httpRequest.getRequestURI();
        

        try {
            log.info("인증 체크 필터 시작 {}", requestURI);
            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) { //Session check
                    log.info("미인증 사용자 요청 {}", requestURI);
                    //로그인으로 redirect
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);//for 이전에 있던 경로로 재 전송
                    return; //여기가 중요, 미인증 사용자는 다음으로 진행하지 않고 끝!
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e; //예외 로깅 가능 하지만, 톰캣까지 예외를 보내주어야 함
        } finally {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
        
    }

    /**
     * 화이트 리스트의 경우 인증 체크X
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}