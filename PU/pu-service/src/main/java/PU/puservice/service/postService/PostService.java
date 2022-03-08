package PU.puservice.service.postService;

import PU.puservice.domain.post.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post);
    Post findPostById(Long id);
    List<Post> getPostList();
    void updatePost(Long id, String title, String body);
    void deletePost(Long id);
}
