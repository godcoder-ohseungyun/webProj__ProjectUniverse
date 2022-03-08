package PU.puservice.service.memberService;

import PU.puservice.domain.member.Member;
import PU.puservice.repository.memberRepository.MemberRepository;
import PU.puservice.repository.memberRepository.MemberRepositoryImpl;
import PU.puservice.repository.memberRepository.UpdateMemberDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class memberServiceImplTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    void join() {
        // given
        Member member1 = new Member("loginId", "password", "mem1");

        // when
        memberService.join(member1);
        Member foundMember = memberService.findMemberByLoginId(member1.getLoginId());

        // then
        assertThat(foundMember).isEqualTo(member1);
    }

    @Test
    void findMemberByLoginId() {
        // given
        Member member = new Member("loginId", "password", "mem1");

        // when
        memberService.join(member);
        Member foundMember = memberService.findMemberByLoginId(member.getLoginId());

        // then
        // 같은 트랜잭션 내에서 찾아왔기 때문에 동일한 객체
        assertThat(member).isEqualTo(foundMember);
    }

    @Test
    void updateMember() {
        // given
        Member member = new Member("loginId", "password", "mem1");
        UpdateMemberDTO umd = new UpdateMemberDTO("newMem", "email@email.com", "1", "newMem_body", "links", "tags");

        // when
        memberService.join(member);
        Member updatedMember = memberService.updateMember(member.getLoginId(), umd);

        // then
        assertThat(updatedMember.getName()).isEqualTo(umd.getName());
        assertThat(updatedMember.getEmail()).isEqualTo(umd.getEmail());
    }
}