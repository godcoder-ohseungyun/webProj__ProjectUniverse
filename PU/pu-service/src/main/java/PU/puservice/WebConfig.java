package PU.puservice;

import PU.puservice.filter.LogFilter;
import PU.puservice.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 필터를 등록
 *
 * 서블릿이 제공하는 FilterRegistrationBean
 *
 * @ServletComponentScan @WebFilter(filterName = "logFilter", urlPatterns = "/*") 로
 * 필터 등록이 가능하지만 필터 순서 조절이 안된다. 따라서 FilterRegistrationBean 을 사용하자.
 */
@Configuration
public class WebConfig {
    /**
     *LogFilter 등록
     */
    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter()); //만든 로그 필터 등록
        filterRegistrationBean.setOrder(1); //체인 동작 우선순위: 오름차순
        filterRegistrationBean.addUrlPatterns("/*"); //필터를 적용할 URL 지정: /*는 전체를 의미
        return filterRegistrationBean;
    }

    /**
     * LoginCheckFilter 등록
     */
    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new
                FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}