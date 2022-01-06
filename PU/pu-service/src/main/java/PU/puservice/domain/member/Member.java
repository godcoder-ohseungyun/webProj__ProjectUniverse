package PU.puservice.domain.member;

import PU.puservice.domain.profile.Profile;
import lombok.Data;

@Data //getter setter를 자동으로 생성해주는 lombok annotation
public class Member {
    private Long id; //PK: Member_id: 이름 변경하지 말것

    private String LoginId;
    private String password;
    private String name;
    private String email = "";
    private Profile profile = null; //프로필 객체 has-a
    private Grade grade = null;
    private int Participation = 0; //참여한 프로젝트 수 -> grade에 영향줄 예정

    public Member(Long id, String loginId, String password, String name) {
        this.id = id;
        LoginId = loginId;
        this.password = password;
        this.name = name;
    }
}
