package PU.puservice.service.postService;

import PU.puservice.domain.post.Post;
import PU.puservice.repository.postRepository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class PostServiceImplTest {

    @Autowired PostRepository postRepository;
    @Autowired PostService postService;

    @Test
    void createPost() {
        // given
        LocalDate now = LocalDate.now();
        Post post = new Post();
        post.setTitle("AAA");
        post.setBody("body_hello");

        // when
        postService.createPost(post);
        Post foundPost = postService.findPostById(post.getId());

        // then
        assertThat(foundPost).isEqualTo(post);
    }

    @Test
    void getPostList() {
        // given
        LocalDate now = LocalDate.now();
        Post post1 = new Post();
        post1.setTitle("AAA");
        post1.setBody("body_aaa");

        Post post2 = new Post();
        post2.setTitle("BBB");
        post2.setBody("body_bbb");

        Post post3 = new Post();
        post3.setTitle("CCC");
        post3.setBody("body_ccc");

        // when
        postService.createPost(post1);
        postService.createPost(post2);
        postService.createPost(post3);
        List<Post> gotPostList = postService.getPostList();

        // 3개의 post가 잘 저장되는지 test
        // then
        assertThat(gotPostList.size()).isEqualTo(3);
        assertThat(gotPostList).contains(post1, post2, post3);
    }

    @Test
    void updatePost() {
        // given
        LocalDate now = LocalDate.now();
        Post post = new Post();
        post.setTitle("AAA");
        post.setBody("body_aaa");

        Post post_update = new Post();
        post_update.setTitle("testPost");
        post_update.setBody("testPost_desc");

        // when
        postService.createPost(post);
        postService.updatePost(post.getId(), post_update.getTitle(), post_update.getBody());
        Post foundPost = postService.findPostById(post.getId());
        foundPost.setViewCnt(100);

        // post update시 내용이 잘 바뀌는지 test
        // then
        assertThat(foundPost.getTitle()).isEqualTo(post_update.getTitle());
        assertThat(foundPost.getBody()).isEqualTo(post_update.getBody());
        assertThat(foundPost.getViewCnt()).isEqualTo(100);
    }

    @Test
    void deletePost() {
        // given
        LocalDate now = LocalDate.now();
        Post post = new Post();
        post.setTitle("AAA");
        post.setBody("body_aaa");

        // when
        postService.createPost(post);
        postService.deletePost(post.getId());

        // post가 삭제되어 repository의 size가 0인지 확인
        // then
        assertThat(postService.getPostList().size()).isEqualTo(0);
    }
}
