package PU.puservice.controller.postController;

import PU.puservice.domain.member.Member;
import PU.puservice.domain.post.MasterKey;
import PU.puservice.domain.post.Post;
import PU.puservice.service.postService.PostService;
import PU.puservice.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/post")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String posts(Model model) {
        List<Post> postList = postService.getPostList();
        model.addAttribute(postList);
        return "post/posts";
    }

    /**
     * 수정자: 오승윤
     *
     * 로그인한 사람이 작성자 인지 아닌지에 따라서 보여주는게 달라야함
     * 세션 로그인 데이터랑 포스트 작성자랑 일치할때 싱글톤 마스터키를 부여
     * 마스터키는 뷰가 확인할수있는 추가 구분자임
     * 마스터키가 온경우 전체데이터 보여주고 아닌경우 일부만 보여줌
     * >이로써 관리자 창 기능 구현가능
     */
    @GetMapping("/{postId}")
    @ResponseBody
    public Object postDetail(@PathVariable Long postId,HttpServletRequest request) {
        Post foundPost = postService.findPostById(postId);
        log.info(foundPost.getWriter());
        String Writer = foundPost.getWriter();

        /**
         * 세션에는 맴버 객체가 저장되어있다.
         *
         */
        HttpSession session = request.getSession(false);

        //새션이 존재하면
        if (session!=null) {
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

            //로그인회원이 작성자일때
            if (loginMember.getLoginId().equals(Writer)) { // why? LoginId에 닉네임을 넣기로 함
                List<Object> list = new ArrayList<Object>();
                list.add(foundPost);
                list.add(MasterKey.masterKey); //싱글톤 객체

                return list;

            }
        }

        return foundPost; //일부 게시판 정보 json

    }

    @GetMapping("/create")
    public String createPostForm() {
        return "post/createPostForm";
    }

    //여기 수정해야함. json으로 넘어오니까
    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post, RedirectAttributes redirectAttributes ,HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        //새션이 존재하면
        if (session != null) {
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            Post createdPost = postService.createPost(post);

            createdPost.setWriter(loginMember.getLoginId()); //로그인 한 사람으로 작성자 지정

            // redirect할 때 url 인코딩 및 쿼리 파라미터 처리
//            redirectAttributes.addAttribute("postId", createdPost.getId());
//            redirectAttributes.addAttribute("status", true);

            // 중복 저장을 막기 위해 PRG 패턴 적용
            return "redirect:/post/{postId}";
        }

        return "redirect:/post";
    }

    // 이미 저장되어있는 post 객체를 찾아야하므로 postId를 pathVariable로 등록
    @GetMapping("{postId}/update")
    public String updatePostForm(@PathVariable Long postId, Model model) {
        Post foundPost = postService.findPostById(postId);
        model.addAttribute(foundPost);
        return "post/updatePostForm";

    }

    @PostMapping("{postId}/update")
    public String updatePost(@PathVariable Long postId, @ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        postService.updatePost(postId, post);

        // redirect할 때 쿼리 파라미터 처리
        redirectAttributes.addAttribute("status", true);

        // 중복 저장을 막기 위해 PRG 패턴 적용
        return "redirect:/post/{postId}";
    }
}
