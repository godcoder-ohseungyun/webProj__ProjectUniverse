package PU.puservice.domain.loginForm;

import lombok.Data;

/**
 * login 시도시 login form 데이터를 받을 domain
 */
@Data
public class LoginForm {
    private String loginId;
    private String password;
}
