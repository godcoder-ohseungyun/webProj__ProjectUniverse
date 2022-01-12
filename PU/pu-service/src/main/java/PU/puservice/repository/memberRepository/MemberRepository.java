package PU.puservice.repository.memberRepository;

import PU.puservice.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Member findById(Long id);

    //Member findByName(String name);

    List<Member> findAll();

    Optional<Member> findByLoginId(String loginId);

    void update(Long id, Member updateParam);

    void delete(Long id);

    void clearStore();
}
