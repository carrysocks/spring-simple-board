package CrudBoard.board.repository;

import CrudBoard.board.domain.Member;
import CrudBoard.board.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post){
        em.persist(post);
    }

    public Post findOne(Long id){
        return em.find(Post.class, id);
    }

    // 검색 기능 구현
    // public List<Post> findAll(PostSearch postSearch){}

    public List<Post> findAllPosts(){
        return em.createQuery("select m from Post m", Post.class)
                .getResultList();
    }

    public void delete(Post post){
        em.remove(post);
    }



}
