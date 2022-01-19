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

    /**
     * findFirst는 없으면 NPE 발생
     * 스트림이 빈경우는 NULL
     */
    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    @Override
    public Member update(String LoginId, Member updateParam) {
        Member findmember = findByLoginId(LoginId).orElse(null);

        if(findmember != null) {
            findmember.setName(updateParam.getName());
            findmember.setEmail(updateParam.getEmail());
            findmember.setPhoto(updateParam.getPhoto());
            findmember.setBody(updateParam.getBody());
            findmember.setLinks(updateParam.getLinks());
            findmember.setTags(updateParam.getTags());
        }

        return findmember;
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
