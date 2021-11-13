package PU.puservice.repository.memberRepository;

import PU.puservice.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class memberRepositoryImpl implements memberRepository {

    private Map<Long,Member> store = new ConcurrentHashMap<>();

    @Override
    public Member save(Member member) {
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public void update(Long id, Member updateParam) {
        Member findmember = findById(id);
        findmember.setId(updateParam.getId());
        findmember.setName(updateParam.getName());
        findmember.setGrade(updateParam.getGrade());
    }

    @Override
    public void delete(){
        //탈퇴
    }

    @Override
    public void clearStore() {
        store.clear();
    }

}
