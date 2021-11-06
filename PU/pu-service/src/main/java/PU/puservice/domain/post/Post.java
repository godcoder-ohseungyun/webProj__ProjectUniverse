package PU.puservice.domain.post;

import lombok.Data;

@Data //getter setter만 쓸것
public class Post {

    private Long id;
    private String title;
    private String body;
    private String writer;

    public Post(Long id, String title, String body, String writer) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.writer = writer;
    }
}
