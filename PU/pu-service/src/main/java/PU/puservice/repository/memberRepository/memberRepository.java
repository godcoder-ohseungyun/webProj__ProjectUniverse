package PU.puservice.repository.memberRepository;

import PU.puservice.domain.member.Member;

import java.util.List;

public interface memberRepository {
    Member save(Member member);
    Member findById(Long id);
    List<Member> findAll();
    void update(Long id,Member updateParam);
    void delete(Long id);
    void clearStore();
}
