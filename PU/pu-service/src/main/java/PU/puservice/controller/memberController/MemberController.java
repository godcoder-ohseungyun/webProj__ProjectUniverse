package PU.puservice.controller.memberController;

import PU.puservice.domain.member.Member;
import PU.puservice.service.memberService.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


/**
 * @Author 오승윤
 * logic: Member & Profile Controller
 *
 * 회원에 대한 관리를 담당하는 컨트롤러입니다.
 * 1.회원 가입 및 탈퇴
 * 2.회원 프로필 관리
 * 기능을 보유합니다.
 *
 * @URL
 * 회원 가입 /join
 *
 * 프로필 조회 /{memberid}
 * 프로필 수정 /{memberid}
 * 프로필 삭제 /{memberId}
 */
@Slf4j
@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


    /**
     * 1.회원 가입 및 탈퇴
     */
    @GetMapping("/join")
    public String joinMemberForm(){
        return "member/joinMember";
    }


    @PostMapping("/join")
    public String joinMember(@ModelAttribute Member member, RedirectAttributes redirectAttributes){

        if(memberService.isPossible(member.getId())) {
            Member savedMember = memberService.join(member);
        }

        redirectAttributes.addAttribute("memberName",member.getName());
        redirectAttributes.addAttribute("status",true);
        log.info(member.getName()+"의 회원가입 완료됨");

        return "redirect:";

        /**
         * redirect: 해당 url로 get방식 request
         */
    }



    /**
     * 2.회원 프로필 관리
     */
    @GetMapping("/{memberId}")
    public String viewMember(@PathVariable String memberId){

        Optional<Member> findMember = memberService.findMemberByLoginId(memberId);

        log.info(findMember.get().getName());

        return "member/profile";
    }

    @PatchMapping("/{memberId}")
    public String updateMember(@PathVariable String memberId){
        return null;
    }

    @DeleteMapping("/{memberId}")
    public String deleteMember(@PathVariable String memberId){
        memberService.out(Long.parseLong(memberId));
        return "redirect:";
    }

}
