package PU.puservice.domain.post.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

// 게시글 생성Form 전송 객체
@Data
public class CreatePostForm {

    @NotBlank
    @Size(min = 3, max = 20)
    private String title;

    @NotBlank
    @Size(min = 20, max = 1000)
    private String body;

    // 임시로 검증
    @NotBlank
    private String writer;

    private LocalDate creationDate;

    public CreatePostForm(String title, String body, String writer, LocalDate creationDate) {
        this.title = title;
        this.body = body;
        this.writer = writer;
        this.creationDate = creationDate;
    }
}
