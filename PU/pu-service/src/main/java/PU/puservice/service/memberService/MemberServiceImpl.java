package PU.puservice.service.memberService;

import PU.puservice.domain.member.Member;
import PU.puservice.repository.memberRepository.MemberRepository;
import PU.puservice.repository.memberRepository.UpdateMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public Member join(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findMemberByLoginId(String loginId){
        return memberRepository.findByLoginId(loginId);
    }

    @Override
    public Member updateMember(String loginId, UpdateMemberDTO umd) {
        return memberRepository.update(loginId, umd);
    }

    // validation으로 대체
//    @Override
//    public boolean isPossibleLoginId(String loginId) {
//
//        return !memberRepository.findByLoginId(loginId).isPresent();
//
//    }

    @Override
    public void delete(String loginId) {
        memberRepository.delete(loginId);
    }
}
