package PU.puservice.repository.memberRepository;

import PU.puservice.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    List<Member> findAll();

    Member findByLoginId(String loginId);

    Member update(String LoginId, UpdateMemberDTO umd);

    void delete(String loginId);
}
