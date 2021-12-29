package PU.puservice.service.memberService;

import PU.puservice.domain.member.Member;

import java.util.Optional;

public interface MemberService {
    Member join(Member member);
    Member findMemberByUniqueId(Long id);
    Optional<Member> findMemberByLoginId(String LoginId);
    boolean isPossible(Long id);
    void out(Long id);
}
