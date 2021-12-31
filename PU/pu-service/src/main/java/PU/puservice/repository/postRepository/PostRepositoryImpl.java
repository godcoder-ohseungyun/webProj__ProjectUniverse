package PU.puservice.repository.postRepository;

import PU.puservice.domain.post.Post;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepositoryImpl implements PostRepository{
    private static Map<Long, Post> store = new HashMap<>();
    private static long sequence = 0L;

    // test용 메소드
    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    @Override
    public Post save(Post post) {
        post.setId(++sequence);
        LocalDate now = LocalDate.now();
        post.setCreationDate(now);
        store.put(post.getId(), post);
        return post;
    }

    @Override
    public Post findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long id, Post updateParam) {
        Post foundPost = store.get(id);
        foundPost.setTitle(updateParam.getTitle());
        foundPost.setBody(updateParam.getBody());
        foundPost.setViewCnt(updateParam.getViewCnt());
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
