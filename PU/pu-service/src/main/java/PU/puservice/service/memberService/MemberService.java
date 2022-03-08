package PU.puservice.service.memberService;

import PU.puservice.domain.member.Member;
import PU.puservice.repository.memberRepository.UpdateMemberDTO;

import java.util.Optional;

public interface MemberService {
    Member join(Member member);
    Member findMemberByLoginId(String loginId);
    Member updateMember(String LoginId, UpdateMemberDTO umd);
//    boolean isPossibleLoginId(String loginId);
    void delete(String loginId);
}
