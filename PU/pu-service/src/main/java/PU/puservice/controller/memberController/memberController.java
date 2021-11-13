package PU.puservice.controller.memberController;

import PU.puservice.service.memberService.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 회원 등록 /member
 * 회원 수정 /member/{memberId}
 * 회원 삭제 /member/{memberId}
 */
@Controller
@RequestMapping("/member")
public class memberController {

    private memberService memberService;

    @Autowired
    public memberController(memberService memberService){
        this.memberService = memberService;
    }

    @PostMapping
    public String joinMember(){
        return null;
    }

    @PatchMapping("/{memberId}")
    public String updateMember(@PathVariable String memberId){
        return null;
    }

    @DeleteMapping("/{memberId}")
    public String deleteMember(@PathVariable String memberId){
        return null;
    }




}
