package PU.puservice.service.postService;

import PU.puservice.domain.post.Post;
import PU.puservice.repository.postRepository.PostRepository;
import PU.puservice.repository.postRepository.PostRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class postServiceImplTest {
    PostRepository postRepository = new PostRepositoryImpl();
    PostService postService = new PostServiceImpl(postRepository);

    @AfterEach
    void afterEach() {
        postRepository.setSequence(0L);
        postRepository.clearStore();
    }

    @Test
    void createPost() {
        // given
        LocalDate now = LocalDate.now();
        Post post = new Post(1L, "testPost", "testPost_desc", "Lee", now);

        // when
        postService.createPost(post);
        Post foundPost = postService.findPostById(1L);

        // then
        assertThat(foundPost).isEqualTo(post);
    }

    @Test
    void getPostList() {
        // given
        LocalDate now = LocalDate.now();
        Post post1 = new Post(1L, "testPost1", "testPost1_desc", "Lee1", now);
        Post post2 = new Post(2L, "testPost2", "testPost2_desc", "Lee2", now);
        Post post3 = new Post(3L, "testPost3", "testPost3_desc", "Lee3", now);

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
        Post post1 = new Post(1L, "testPost1", "testPost1_desc", "Lee1", now);
        Post post2 = new Post(2L, "testPost2", "testPost2_desc", "Lee2", now);

        // when
        postService.createPost(post1);
        postService.updatePost(post1.getId(), post2);
        Post foundPost = postService.findPostById(post1.getId());
        foundPost.setViewCnt(100);

        // post update시 id는 바뀌지 않고 내용이 잘 바뀌는지 test
        // then
        assertThat(foundPost.getId()).isEqualTo(1L);
        assertThat(foundPost.getTitle()).isEqualTo("testPost2");
        assertThat(foundPost.getBody()).isEqualTo("testPost2_desc");
        assertThat(foundPost.getViewCnt()).isEqualTo(100);
    }

    @Test
    void deletePost() {
        // given
        LocalDate now = LocalDate.now();
        Post post1 = new Post(1L, "testPost1", "testPost1_desc", "Lee1", now);

        // when
        postService.createPost(post1);
        postService.deletePost(post1.getId());

        // post가 삭제되어 repository의 size가 0인지 확인
        // then
        assertThat(postService.getPostList().size()).isEqualTo(0);
    }
}
