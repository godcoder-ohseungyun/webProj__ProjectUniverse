package PU.puservice.domain.member;

import PU.puservice.domain.profile.Profile;
import lombok.Data;

@Data //getter setter를 자동으로 생성해주는 lombok annotation
public class Member {
    private Long id; //PK: Member_id: 이름 변경하지 말것 : 애는 관리자용 구분키로 쓰일꺼임

    private String LoginId; //이미 구축해놓은것을 바꾸기 에매함 LoginId를 닉네임이자 unique key로 할것 POST의 Writer도 LoginId를 입력함
    private String password;
    private String email;
    private Profile profile = null; //프로필 객체 has-a
    private Grade grade = null;
    private int Participation = 0; //참여한 프로젝트 수 -> grade에 영향줄 예정

    public Member(Long id, String loginId, String password, String email) {
        this.id = id;
        this.LoginId = loginId;
        this.password = password;
        this.email = email;
    }
}
