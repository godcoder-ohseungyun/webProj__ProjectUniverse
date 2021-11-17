package PU.puservice;


import PU.puservice.domain.member.Member;
import PU.puservice.service.memberService.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor //final이 붙은 필드의 생성자를 자동으로 생성해주는 lombok
public class TestDataInit {

    private final MemberService memberService;

    /**
    //RequiredArgsConstructor 에너테이션으로 final 필드 생성자 자동 생성

    public TestDataInit(memberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
     */


    /**
     * 빈 생명주기 콜백: 초기화 콜백 메서드
     * TestCode
     */
    @PostConstruct
    public void init(){
        Member memberA = new Member(1L,"0602","0602","시작1호기");
        memberService.join(memberA);
    }
}
