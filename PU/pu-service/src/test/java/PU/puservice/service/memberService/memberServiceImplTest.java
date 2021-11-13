package PU.puservice.service.memberService;

import PU.puservice.domain.member.Member;
import PU.puservice.repository.memberRepository.memberRepository;
import PU.puservice.repository.memberRepository.memberRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;





class memberServiceImplTest {

    private memberRepository memberRepository = new memberRepositoryImpl();
    private memberService memberService = new memberServiceImpl(memberRepository);


    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        Member member1 = new Member(1L,"오승윤");
        memberService.join(member1);
        Member find = memberService.findMember(1l);
        assertThat(find).isEqualTo(member1);
    }
}