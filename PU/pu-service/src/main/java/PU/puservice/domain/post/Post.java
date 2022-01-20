package PU.puservice.domain.post;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("PostInfo")
@ApiModel(description = "게시물 데이터를 저장하는 도메인 객체, 클라이언트는 title과 body만 입력합니다.")
public class Post {
    private Long id;

    private String title;
    private String body;

    private String writer; //LoginId가 저장될 예정
    private LocalDate creationDate = null;
    private int viewCnt = 0;

    @ApiParam(value = "이 부분은 작성자 본인만 볼수있도록 서버측에서 제한합니다.", required = false)
    private List<String> volunteers; //지원자들 목록 but 게시자만 이 목록 볼수있게 할꺼임

}
