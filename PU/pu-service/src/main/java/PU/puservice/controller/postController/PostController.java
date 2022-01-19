package PU.puservice.controller.postController;

import PU.puservice.domain.member.Member;
import PU.puservice.domain.post.Post;
import PU.puservice.service.postService.PostService;
import PU.puservice.session.SessionConst;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.List;


/**
 *
 * /posts
 * /posts/{postId}
 */

@Slf4j
@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post>  posts() {

        List<Post> postList = postService.getPostList();

        return postList;
    }

    /**
     * 본인 요청시 전체 데이터 반환
     * 피 본인 요청시 일부 데이터 반환
     *
     * *도메인에 필터를 걸어 객체 반환하기 -> 아래 필터 메서드 정의 되어있음
     * *반환 타입: MappingJacksonValue 이용
     */
    @GetMapping("/{postId}")
    public MappingJacksonValue postDetail(@PathVariable Long postId,HttpServletRequest request) {

        Post foundPost = postService.findPostById(postId);
        String WriterLoginId = foundPost.getWriter();

        /**
         * 세션에는 맴버 객체가 저장되어있다.
         */
        HttpSession session = request.getSession(false);

        //새션이 존재하면
        if (session!=null) {
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

            //로그인회원이 작성자일때
            if (loginMember.getLoginId().equals(WriterLoginId)) {
                MappingJacksonValue mapping = new MappingJacksonValue(foundPost);
                mapping.setFilters(ALLPostInfo()); //모든정보 허용
                return mapping;

            }
        }

        MappingJacksonValue mapping = new MappingJacksonValue(foundPost);
        mapping.setFilters(PARTPostInfo()); //일부정보 허용

        return mapping;

    }


    /**
     * 프론트 측에서 header에 담아준 url로 redirect 걸어야함
     *
     * ->새로고침시 중복등록 방지를 위해서
     */
    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        
        Post createdPost = postService.createPost(post);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath() // 기본 uri
                .path("/posts")
                .path("/{postId}")
                .buildAndExpand(createdPost.getId())
                .toUri();
        
        return ResponseEntity.created(location).build(); //header에 location에 uri 담음
    }

    /**
     * 프론트 측에서 header에 담아준 url로 redirect 걸어야함
     * ->새로고침시 중복등록 방지를 위해서
     */
    @PatchMapping("{postId}/update")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post post) {

        postService.updatePost(postId, post);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath() // 기본 uri
                .path("/posts")
                .path("/{postId}")
                .buildAndExpand(postId)
                .toUri();

        return ResponseEntity.created(location).build();
    }


    /**
     * domain.post.Post.class   @JsonFilter("PostInfo")
     * 해당 도메인 데이터에 필터기능 추가
     * restAPI 필터 수업에 있음
     */

    //모든 정보를 허용
    private FilterProvider ALLPostInfo(){
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "title", "body", "writer","creationDate","viewCnt","volunteers");

        FilterProvider filters = new SimpleFilterProvider().addFilter("PostInfo", filter);

        return filters;
    }

    //일부 정보를 허용
    private FilterProvider PARTPostInfo(){
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "title", "body", "writer","creationDate","viewCnt");

        FilterProvider filters = new SimpleFilterProvider().addFilter("PostInfo", filter);

        return filters;
    }
}
