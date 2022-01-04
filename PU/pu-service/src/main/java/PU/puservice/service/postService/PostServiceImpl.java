package PU.puservice.service.postService;

import PU.puservice.domain.post.Post;
import PU.puservice.repository.postRepository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    @Override
    public void updatePost(Long id, Post updateParam) {
        postRepository.update(id, updateParam);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.delete(id);
    }
}
