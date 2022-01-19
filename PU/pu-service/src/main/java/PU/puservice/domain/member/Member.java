package PU.puservice.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data //getter setter를 자동으로 생성해주는 lombok annotation
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Long id; //PK: Member_id: 이름 변경하지 말것

    private String LoginId;  //유니크 해야함
    private String password;
    private String name;
    private String email = "";
    private Grade grade = null;
    private int Participation = 0; //참여한 프로젝트 수 -> grade에 영향줄 예정

    //프로필 데이터
    private String photo; // 이부분은 추후에 파일 업로드 공부해서 바꿔야 할듯
    private String body;
    private String links;
    private String tags;
    private List<String> projectList; //참여하고있는 공고 진행중인 프로젝트 리스트

}
