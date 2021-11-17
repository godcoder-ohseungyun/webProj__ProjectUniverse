package PU.puservice.service.loginService;

import PU.puservice.domain.member.Member;
import PU.puservice.repository.memberRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginServiceImple implements MemberLoginService{

    private final MemberRepository memberRepository;

    //@Autowired 생략가능

    /**
     * @param loginId 사용자 입력 id
     * @param password 사용자 입력 pw
     * @return loginid로 member store 조회 저장된 객체와 pw 일치하면 해당 객체 반환 else null
     */
    @Override
    public Member login(String loginId,String password){
        return memberRepository.findByLoginId(loginId).filter(m->m.getPassword().equals(password)).orElse(null);
    }
}
