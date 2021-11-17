package PU.puservice.controller.memberController;

import PU.puservice.domain.member.Member;
import PU.puservice.service.memberService.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * @Author 오승윤
 * logic: 회원가입 처리
 *
 * 회원 등록 /member
 * 회원 수정 /member/{memberId}
 * 회원 삭제 /member/{memberId}
 */
@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping
    public String joinMemberForm(){
        return "/member";
    }

    /**TODO: 검증 추가하기*/
    @PostMapping
    public String joinMember(@ModelAttribute Member member, RedirectAttributes redirectAttributes){

        if(memberService.isPossible(member.getId())) {
            Member savedMember = memberService.join(member);
        }

        redirectAttributes.addAttribute("memberName",member.getName());
        redirectAttributes.addAttribute("status",true);

        return "redirect:/basic/home";
    }

    /** 계층구조 고려하기
    @PatchMapping("/{memberId}")
    public String updateMember(@PathVariable String memberId){
        return null;
    }

    @DeleteMapping("/{memberId}")
    public String deleteMember(@PathVariable String memberId){
        memberService.out(Long.parseLong(memberId));
        return "redirect:/basic/home";
    }
    */



}
