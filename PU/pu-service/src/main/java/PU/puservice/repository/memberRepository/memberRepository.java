package PU.puservice.repository.memberRepository;

import PU.puservice.domain.member.Member;

public interface memberRepository {
    Member save(Member member);
    Member findById(Long id);
    void update(Long id,Member updateParam);
    void delete();
    void clearStore();
}
