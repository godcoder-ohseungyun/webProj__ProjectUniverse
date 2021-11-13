package PU.puservice.service.memberService;

import PU.puservice.domain.member.Member;
import PU.puservice.repository.memberRepository.memberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class memberServiceImpl implements memberService{

    private memberRepository memberRepository;

    @Autowired //자동 생성자 주입
    public  memberServiceImpl(memberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public Member join(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id);
    }
}
