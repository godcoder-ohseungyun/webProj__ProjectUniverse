package PU.puservice.service.memberService;

import PU.puservice.domain.member.Member;
import PU.puservice.repository.memberRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {


    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member join(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findMemberByUniqueId(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Optional<Member> findMemberByLoginId(String LoginId){
        return memberRepository.findByLoginId(LoginId);
    }

    @Override
    public Member updateMember(String LoginId, Member updateParam) {
        return memberRepository.update(LoginId,updateParam);
    }


    @Override
    public boolean isPossibleLoginId(String LoginId) {

        return !memberRepository.findByLoginId(LoginId).isPresent();

    }

    @Override
    public void out(Long id) {
        memberRepository.delete(id);
    }
}
