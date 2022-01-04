package PU.puservice.domain.post;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Post {
    private Long id;
    private String title;
    private String body;

    // 작성자는 차후에 생성자에서 제외하고 Member 객체에서 이름 찾아서 설정하는 것으로 수정
    private String writer;
    private LocalDate creationDate;
    private int viewCnt = 0;

    public Post() {

    }

    public Post(Long id, String title, String body, String writer, LocalDate creationDate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.writer = writer;
        this.creationDate = creationDate;
    }

    // 회원들이 게시글 열람할 때마다 조회수 증가하는 로직 추가
}
