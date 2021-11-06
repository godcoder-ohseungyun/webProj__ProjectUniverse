package PU.puservice.domain.member;

import lombok.Data;

@Data //getter setter만 쓸것
public class Member {
    private Long id; //long 타입 주의
    private String name;
    private Grade grade = null;

    public Member(Long id,String name){
        this.id = id;
        this.name = name;
    }

}
