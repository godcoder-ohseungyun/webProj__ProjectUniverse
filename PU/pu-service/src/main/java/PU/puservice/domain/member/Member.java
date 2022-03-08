package PU.puservice.domain.member;

import PU.puservice.domain.apply.MsgForm;
import PU.puservice.domain.post.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Data // getter setter를 자동으로 생성해주는 lombok annotation
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "회원정보를 저장하는 도메인 객체")
@Entity
// Unique 제약조건 추가
@Table(name = "Member", uniqueConstraints = @UniqueConstraint(columnNames = {"member_id"}))
public class Member {

    @Id
    @Column(name = "member_id")
    @NotNull
    private String loginId;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ApiParam(value = "프로필에 표시될 데이터 목록", required = false)
    private String name;
    private String email = "";
    private String photo; // 이 부분은 추후에 파일 업로드 공부해서 바꿔야 할듯
    private String body;
    private String links;
    private String tags;

    @OneToMany(mappedBy = "member")
    private List<Post> projectList = new LinkedList<>(); // 내가 참여하고있는 공고 진행중인 프로젝트

    private LinkedList<MsgForm> msgs = new LinkedList<>(); // 알림 데이터 창

    public Member(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

}

