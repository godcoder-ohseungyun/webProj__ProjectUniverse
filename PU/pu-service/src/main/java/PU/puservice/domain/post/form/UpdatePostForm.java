package PU.puservice.domain.post.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// 게시글 수정Form 전송 객체
@Data
public class UpdatePostForm {

    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String title;

    @NotBlank
    @Size(min = 20, max = 1000)
    private String body;

    // 게시글 수정하는 시점에 사용자의 이름이 바뀌어있을 수도 있으므로 writer도 수정 가능
    // 임시로 검증
    @NotBlank
    private String writer;

    public UpdatePostForm(Long id, String title, String body, String writer) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.writer = writer;
    }
}
