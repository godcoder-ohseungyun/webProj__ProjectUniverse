package PU.puservice.service.memberService;

import PU.puservice.domain.member.Member;
import PU.puservice.repository.memberRepository.MemberRepository;
import PU.puservice.repository.memberRepository.MemberRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;




class memberServiceImplTest {

    private MemberRepository memberRepository = new MemberRepositoryImpl();
    private MemberService memberService = new MemberServiceImpl();
    
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member1 = new Member(1l,"00","00","시작2호기");
        //when
        memberService.join(member1);
        Member find = memberService.findMemberByUniqueId(1l);
        //then
        assertThat(find).isEqualTo(member1);
    }

    @Test
    void isPossible(){
        //given
        Member member1 = new Member(1l,"00","00","시작2호기");
        memberService.join(member1);
        //when
        boolean result = memberService.isPossible(1l);
        //then
        assertThat(result).isEqualTo("true");
    }
}