package PU.puservice.controller.memberController;

import PU.puservice.domain.member.Member;
import PU.puservice.service.memberService.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


/**
 * @Author 오승윤
 * logic: Member & Profile Controller
 *
 * 회원에 대한 관리를 담당하는 컨트롤러입니다.
 * 1.회원 가입 
 * 2.회원 프로필 관리
 * 기능을 보유합니다.
 * 
 * Todo: 예외 헨들링 사용자 정의 예외로 만들고 AOP 적용
 *
 * @URL
 * 회원 /members
 *
 * 프로필 /members/{LoginId}
 */
@Slf4j
@RestController()
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


    /**
     * 회원가입
     * 사용자가 입력한 LoginId 중복검사후 
     * 이상없으면 회원가입 진행
     */
    @PostMapping
    public ResponseEntity<Member> joinMember(@RequestBody Member member) throws Exception {

        if (memberService.isPossibleLoginId(member.getLoginId())) {

            Member savedMember = memberService.join(member);

            
            //url 수정 필요
            URI location = ServletUriComponentsBuilder.fromCurrentRequest() //최근 요청 URL에
                    .path("/{LoginId}") //추가한다.
                    .buildAndExpand(savedMember.getLoginId()) //{id}에 해당 값을
                    .toUri(); //전체를 uri로 변환

            log.info("저장완료");
            return ResponseEntity.created(location).build();
        }

        throw new Exception("ID가 유효하지 않습니다.");
    }



    /**
     * 2.회원 정보 조회 : 프로필
     */
    @GetMapping("/{LoginId}")
    public Member viewMember(@PathVariable String LoginId) throws Exception {

        Member findMember = memberService.findMemberByLoginId(LoginId).orElseThrow(()->new Exception("없는 회원 입니다."));

        return findMember;
    }


    /**
     * 2.회원 정보 수정
     */
    @PatchMapping("/{LoginId}")
    public Member updateMember(@PathVariable String LoginId,@RequestBody Member member) throws Exception {
        return memberService.updateMember(LoginId,member);
    }


}
