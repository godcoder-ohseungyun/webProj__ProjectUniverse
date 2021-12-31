package PU.puservice.domain.post;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Post {
    private Long id;
    private String title;
    private String body;
    private String writer;
    private LocalDate creationDate;
    private int viewCnt = 0;

    public Post(Long id, String title, String body, String writer, LocalDate creationDate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.writer = writer;
        this.creationDate = creationDate;
    }
}
