package PU.puservice;

import PU.puservice.interceptor.LoginCheckInterceptor;
import PU.puservice.logTrace.trace.LogTrace;
import PU.puservice.logTrace.trace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 인터셉터 등록
     * interceptor/LoginCheckInterceptor.java
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        registry.addInterceptor(new LoginCheckInterceptor())
//                .order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/", "/join", "/login", "/logout",
//                        "/css/**", "/*.ico", "/error"
//                );
//    }


}
