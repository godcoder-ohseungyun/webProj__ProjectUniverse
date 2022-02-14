package PU.puservice.domain.apply;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "회원 프로필 메세지 저장리스트에 사용될 객체")
public class MsgForm {
    private boolean isRead; //읽음 표시 여부
    private String msg;
}
