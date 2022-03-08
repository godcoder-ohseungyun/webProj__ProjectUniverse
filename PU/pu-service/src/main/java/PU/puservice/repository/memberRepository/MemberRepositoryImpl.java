package PU.puservice.repository.memberRepository;

import PU.puservice.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager em;

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m").getResultList();
    }

    @Override
    public Member findByLoginId(String loginId) {
        return em.find(Member.class, loginId);
    }

    @Override
    public Member update(String LoginId, UpdateMemberDTO umd) {
        Member foundMember = findByLoginId(LoginId);

        foundMember.setName(umd.getName());
        foundMember.setEmail(umd.getEmail());
        foundMember.setPhoto(umd.getPhoto());
        foundMember.setBody(umd.getBody());
        foundMember.setLinks(umd.getLinks());
        foundMember.setTags(umd.getTags());

        return foundMember;
    }

    @Override
    public void delete(String loginId){
        em.createQuery("delete from Member m where m.id =: loginId").setParameter("loginId", loginId).executeUpdate();
    }

}
