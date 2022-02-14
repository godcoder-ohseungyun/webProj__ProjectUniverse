package PU.puservice.domain.apply;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter setter를 자동으로 생성해주는 lombok annotation
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "승인 요청 json data -> 컨버팅 ")
public class ApproveForm {
    private String loginId;
    private String msg;
    private Long postId;
}
