package PU.puservice.service.postService;

import PU.puservice.domain.post.Post;
import PU.puservice.repository.postRepository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    @Transactional(readOnly = true)
    public Post findPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    @Override
    public void updatePost(Long id, String title, String body) {
        postRepository.update(id, title, body);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.delete(id);
    }
}
