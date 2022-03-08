package PU.puservice.domain.post;

import PU.puservice.domain.member.Member;
import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("PostInfo")
@ApiModel(description = "게시물 데이터를 저장하는 도메인 객체, 클라이언트는 title과 body만 입력합니다.")
@Entity
public class Post {
    @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String writer = "memberName";
    private LocalDate creationDate = null; // 등록일 이후 일정 기간이 지나면 삭제: 공고 가능기간 제어
    private int viewCnt = 0;

    @ApiParam(value = "이 부분은 작성자 본인만 볼수있도록 서버측에서 제한합니다.", required = false)
    @OneToMany(mappedBy = "post")
    private List<Member> volunteers = new LinkedList<>(); // 지원자들 목록 but 게시자만 이 목록 볼 수 있게

//    public Post(String title, String body) {
//        this.title = title;
//        this.body = body;
//    }

}
