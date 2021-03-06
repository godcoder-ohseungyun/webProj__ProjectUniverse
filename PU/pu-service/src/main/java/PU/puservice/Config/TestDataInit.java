package PU.puservice.Config;


import PU.puservice.controller.memberController.LoginController;
import PU.puservice.domain.member.Member;
import PU.puservice.domain.post.Post;
import PU.puservice.service.loginService.MemberLoginService;
import PU.puservice.service.memberService.MemberService;
import PU.puservice.service.postService.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 빈 생명주기 콜백
 * 로직 테스트를 위해 빈 등록 전에 필요 데이터 생성하도록 하기위한 테스트 데이터 관리 class
 */


@Component
@RequiredArgsConstructor //final이 붙은 필드의 생성자를 자동으로 생성해주는 lombok
public class TestDataInit {

    //알아서 주입 됨 RequiredArgsConstructor
    private final MemberService memberService;
    private final PostService postService;
    private final MemberLoginService memberLoginService;

    /**
    //RequiredArgsConstructor 에너테이션으로 final 필드 생성자 자동 생성

    public TestDataInit(memberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
     */


    /**
     * 빈 생명주기 콜백: 초기화 콜백 메서드
     * TestCode: 멤버 로직 테스트를 위해 데이터 미리 생성 삽입
     */
    @PostConstruct
    public void init(){
        //회원가입 회원 하나 생성
        Member memberA = new Member(1L,"startUser","0602","시작1호기");
        memberService.join(memberA);

        //게시물 하나 생성
        Post post1 = new Post(1L,"테스트파일","음","startUser");
        postService.createPost(post1);

        Post post2 = new Post(2l,"테스트파일2","응","new");
        postService.createPost(post2);
    }
}
