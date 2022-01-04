package PU.puservice.controller.postController;

import PU.puservice.domain.post.Post;
import PU.puservice.domain.post.form.CreatePostForm;
import PU.puservice.domain.post.form.UpdatePostForm;
import PU.puservice.service.postService.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Slf4j
@Controller
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

    @GetMapping("/{postId}")
    public String postDetail(@PathVariable Long postId, Model model) {
        Post foundPost = postService.findPostById(postId);
        model.addAttribute(foundPost);
        return "post/postDetail";
    }

    @GetMapping("/create")
    public String createPostForm() {
        return "post/createPostForm";
    }

    // 검증 위해 @Validated annotation 추가
    @PostMapping("/create")
    public String createPost(@Validated @ModelAttribute("post") CreatePostForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        // 에러 검출 시 생성 폼으로 다시 이동
        // bindingResult는 자동으로 view에 넘어가기 때문에 modelAttribute에 담지 않아도 됨
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "post/createPostForm";
        }

        // CreatePostForm 객체를 Post 객체로 변환
        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setBody(form.getBody());
        post.setWriter(form.getWriter());
        post.setCreationDate(form.getCreationDate());

        Post createdPost = postService.createPost(post);

        // redirect할 때 url 인코딩 및 쿼리 파라미터 처리
        redirectAttributes.addAttribute("postId", createdPost.getId());
        redirectAttributes.addAttribute("status", true);

        // 중복 저장을 막기 위해 PRG 패턴 적용
        return "redirect:/post/{postId}";
    }

    // 이미 저장되어있는 post 객체를 찾아야하므로 postId를 pathVariable로 등록
    @GetMapping("{postId}/update")
    public String updatePostForm(@PathVariable Long postId, Model model) {
        Post foundPost = postService.findPostById(postId);
        model.addAttribute(foundPost);
        return "post/updatePostForm";

    }

    @PostMapping("{postId}/update")
    public String updatePost(@PathVariable Long postId, @Validated @ModelAttribute("post") UpdatePostForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "post/updatePostForm";
        }

        // @PathVariable로 받은 id값과 게시글의 id값이 다르면 오류 발생
        // 차후 수정
        if(postId != form.getId()) {
            bindingResult.reject("wrongPostId");
        }

        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setBody(form.getBody());
        post.setWriter(form.getWriter());

        postService.updatePost(postId, post);

        // redirect할 때 쿼리 파라미터 처리
        redirectAttributes.addAttribute("status", true);

        // 중복 저장을 막기 위해 PRG 패턴 적용
        return "redirect:/post/{postId}";
    }
}
