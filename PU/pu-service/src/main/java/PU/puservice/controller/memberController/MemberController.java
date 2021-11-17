package PU.puservice.controller.memberController;

import PU.puservice.domain.member.Member;
import PU.puservice.service.memberService.memberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * 회원 등록 /member
 * 회원 수정 /member/{memberId}
 * 회원 삭제 /member/{memberId}
 */
@Slf4j
@Controller
@RequestMapping("/member")
public class memberController {

    private memberService memberService;

    @Autowired
    public memberController(memberService memberService){
        this.memberService = memberService;
    }

    @GetMapping
    public String joinmember(){
        return "/member/joinMember";
    }

    @PostMapping
    public String joinMember(@ModelAttribute Member member, RedirectAttributes redirectAttributes){

        if(memberService.isPossible(member.getId())) {
            Member savedMember = memberService.join(member);
        }

        redirectAttributes.addAttribute("memberName",member.getName());
        redirectAttributes.addAttribute("status",true);

        return "redirect:/basic/home";
    }

    @PatchMapping("/{memberId}")
    public String updateMember(@PathVariable String memberId){
        return null;
    }

    @DeleteMapping("/{memberId}")
    public String deleteMember(@PathVariable String memberId){
        memberService.out(Long.parseLong(memberId));
        return "redirect:/basic/home";
    }




}
