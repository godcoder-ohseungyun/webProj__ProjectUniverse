package PU.puservice.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 직접 만든 Session: 참고용!
 * Httpsession로직 구현
 * Httpsession로직 파악에 도움됨
 */
@Component
public class MySessionManager {
    public static final String SESSION_COOKIE_NAME = "mySessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();
    /**
     * 세션 생성
     */
    public void createSession(Object value, HttpServletResponse response) {
        //세션 id를 생성하고, 값을 세션에 저장
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);
        //쿠키 생성
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(mySessionCookie);
    }
    /**
     * 세션 조회
     */
    public Object getSession(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME); //아래 메서드 정의 참고
        if (sessionCookie == null) {
            return null;
        }
        return sessionStore.get(sessionCookie.getValue()); //findCookie로 추출한 sessionID를 가지고 store 탐색해서 맵핑된 value 추출
    }
    /**
     * 세션 만료
     */
    public void expire(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
        }
    }
    private Cookie findCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null) {
            return null;
        }
        return Arrays.stream(request.getCookies()) //request http로 넘어온 Cookies 를 Array로 받아 
                .filter(cookie -> cookie.getName().equals(cookieName)) //"mySessionId"와 일치하는 항목을 추출
                .findAny()
                .orElse(null); //or null
    }
}