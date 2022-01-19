package PU.puservice.service.memberService;

import PU.puservice.domain.member.Member;

import java.util.Optional;

public interface MemberService {
    Member join(Member member);
    Member findMemberByUniqueId(Long id);
    Optional<Member> findMemberByLoginId(String LoginId);
    Member updateMember(String LoginId, Member updateParam);
    boolean isPossibleLoginId(String Loginid);
    void out(Long id);
}
