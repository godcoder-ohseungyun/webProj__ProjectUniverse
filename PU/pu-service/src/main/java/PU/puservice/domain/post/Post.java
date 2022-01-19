package PU.puservice.domain.post;

import com.fasterxml.jackson.annotation.JsonFilter;
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
public class Post {
    private Long id;
    private String title;
    private String body;
    private String writer; //LoginId가 저장될 예정
    private LocalDate creationDate = null;
    private int viewCnt = 0;
    private List<String> volunteers; //지원자들 목록 but 게시자만 이 목록 볼수있게 할꺼임

}
