package PU.puservice.domain.post;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class Post {
    private Long id;
    private String title;
    private String body;
    private String writer;
    private LocalDate creationDate;
    private int viewCnt = 0;
    private List<String> volunteers; //지원자들 목록 but 게시자만 이 목록 볼수있게 할꺼임

    public Post(Long id, String title, String body, String writer, LocalDate creationDate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.writer = writer;
        this.creationDate = creationDate;
    }
}
