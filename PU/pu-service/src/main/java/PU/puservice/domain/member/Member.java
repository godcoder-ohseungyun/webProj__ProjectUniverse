package PU.puservice.domain.member;

import lombok.Data;

@Data //getter setter만 쓸것
public class Member {
    private Long id; //PK값 UNIQUE

    private String LoginId;
    private String password;
    private String name;
    private Grade grade = null;
    private int Participation = 0; //참여한 프로젝트 수 -> grade에 영향줄 예정

    public Member(Long id,String name){
        this.id = id;
        this.name = name;
    }
    

}
