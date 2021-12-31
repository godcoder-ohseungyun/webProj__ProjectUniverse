package PU.puservice.controller.postController;

import PU.puservice.domain.post.Post;
import PU.puservice.service.postService.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

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

    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post, RedirectAttributes redirectAttributes) {
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
    public String updatePost(@PathVariable Long postId, @ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        postService.updatePost(postId, post);

        // redirect할 때 쿼리 파라미터 처리
        redirectAttributes.addAttribute("status", true);

        // 중복 저장을 막기 위해 PRG 패턴 적용
        return "redirect:/post/{postId}";
    }
}
