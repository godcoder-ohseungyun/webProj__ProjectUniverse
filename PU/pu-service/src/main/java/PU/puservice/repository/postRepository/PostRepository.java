package PU.puservice.repository.postRepository;

import PU.puservice.domain.post.Post;

import java.util.List;

public interface PostRepository {
    Post save(Post post);
    Post findById(Long id);
    List<Post> findAll();
    void update(Long id, Post updateParam);
    void delete(Long id);
    void clearStore();

    // test용 메소드
    void setSequence(Long sequence);
}
