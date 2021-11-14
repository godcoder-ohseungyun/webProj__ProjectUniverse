package PU.puservice.service.memberService;

import PU.puservice.domain.member.Member;

public interface memberService {
    Member join(Member member);
    Member findMember(Long id);
    boolean isPossible(Long id);
    void out(Long id);
}
