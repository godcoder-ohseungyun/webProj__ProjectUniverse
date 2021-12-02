package PU.puservice.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * 서블릿이 제공하는 Filter interface 구현
 * init(): 필터 초기화 메서드, 서블릿 컨테이너가 생성될 때 호출된다.
 * doFilter(): 고객의 요청이 올 때 마다 해당 메서드가 호출된다. 필터의 로직을 구현하면 된다.
 * destroy(): 필터 종료 메서드, 서블릿 컨테이너가 종료될 때 호출된다.
 */
@Slf4j
public class LogFilter implements Filter {

    //Filter interface에 default method로 정의되어있다.
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");

        //ServletRequest는 http외 다른 것도 받을수 있도록 존재하는것
        //단 기능이 별로 없고 쓸일이 거의 없어 HttpServletRequest으로 다운 캐스팅 해서 사용
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try {
            log.info("REQUEST [{}][{}]", uuid, requestURI);
            chain.doFilter(request, response); //. 다음 필터가 있으면 필터를 호출하고, 필터가 없으면 디스패쳐서블릿을 호출한다. (필수로직)
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }

    }

    //Filter interface에 default method로 정의되어있다.
    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
