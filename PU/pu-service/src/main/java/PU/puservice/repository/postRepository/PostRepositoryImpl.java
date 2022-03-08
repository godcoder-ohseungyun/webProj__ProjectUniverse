package PU.puservice.repository.postRepository;

import PU.puservice.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository{

    private final EntityManager em;

    @Override
    public Post save(Post post) {
        LocalDate now = LocalDate.now();
        post.setCreationDate(now);

        // 영속성 컨텍스트에 Post 객체 저장
        em.persist(post);

        return post;
    }

    @Override
    public Post findById(Long id) {
        return em.find(Post.class, id);
    }

    @Override
    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class).getResultList();
    }

    @Override
    public void update(Long id, String title, String body) {
        Post foundPost = findById(id);
        foundPost.setTitle(title);
        foundPost.setBody(body);
    }

    @Override
    public void delete(Long id) {
        // 인수로 받은 id를 사용해 Post 삭제하는 쿼리문 생성
        em.createQuery("delete from Post p where p.id =:postId").setParameter("postId", id).executeUpdate();
    }
}
