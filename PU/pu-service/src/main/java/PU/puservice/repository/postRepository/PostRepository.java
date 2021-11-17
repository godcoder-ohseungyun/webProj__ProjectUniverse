package PU.puservice.repository.postRepository;

import PU.puservice.domain.post.Post;

public interface postRepository {
    Post save(Post post);
    Post findById(Long id);
    void update(Long id, Post updateParam);
    void delete();
    void clearStore();
}
