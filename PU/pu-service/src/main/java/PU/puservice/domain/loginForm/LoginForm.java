package PU.puservice.domain.loginForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * login 시도시 login form 데이터를 받을 domain
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "로그인 폼을 위한 도메인 객체")
public class LoginForm {

    @ApiParam(value = "유니크 해야합니다.", required = true)
    private String loginId;

    @ApiParam(value = "제한사항은 없습니다.", required = true)
    private String password;
}
