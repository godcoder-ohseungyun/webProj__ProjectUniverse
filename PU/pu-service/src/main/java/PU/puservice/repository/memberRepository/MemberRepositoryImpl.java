package PU.puservice.repository.memberRepository;

import PU.puservice.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private Map<Long,Member> store = new ConcurrentHashMap<>();
    private Long uniqueId = 0L; //임시

    @Override
    public Member save(Member member) {
        member.setId(uniqueId++);//임시: 별도 unique id 생성 로직 짜야함
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //values returns map so, convert to ArrayList
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    @Override
    public void update(Long id, Member updateParam) {
        Member findmember = findById(id);
        findmember.setId(updateParam.getId());
        findmember.setName(updateParam.getName());
        findmember.setGrade(updateParam.getGrade());
    }


    @Override
    public void delete(Long id){
        store.remove(id);
    }

    @Override
    public void clearStore() {
        store.clear();
    }

}
