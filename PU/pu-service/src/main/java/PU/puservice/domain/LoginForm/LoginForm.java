package PU.puservice.domain.LoginForm;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * login 시도시 login form 데이터를 받을 domain
 */
@Data
public class LoginForm {

    @NotBlank
    @Size(min = 10, max = 30)
    private String loginId;

    @NotBlank
    @Size(min = 10, max = 30)
    private String password;
}
