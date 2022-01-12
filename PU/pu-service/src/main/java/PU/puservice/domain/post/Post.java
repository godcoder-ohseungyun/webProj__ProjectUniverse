package PU.puservice.domain.post;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class Post {
    private Long id;
    private String title;
    private String body;
    private String writer; //게시자 LoginId가 저장되어야함 => 닉네임
    private LocalDate creationDate = null;
    private int viewCnt = 0;
    private Set<String> volunteers; //지원자들 목록 but 게시자만 이 목록 볼수있게 할꺼임 //중복 허용x set으로

    public Post(Long id, String title, String body, String writer, LocalDate creationDate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.writer = writer;
        this.creationDate = creationDate;
        this.volunteers = new HashSet<>(); //조회에 특출난 HashSet사용
    }
}
