package CrudBoard.board.repository;

import CrudBoard.board.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void save(Comment comment){
        em.persist(comment);
    }

    public Comment findOne(Long id){
        return em.find(Comment.class, id);
    }

    public List<Comment> findByPostId(Long postId){
        TypedQuery<Comment> query = em.createQuery("select c from Comment c join c.post p where p.id =:post_id",
                Comment.class);
        query.setParameter("post_id", postId);
        return query.getResultList();
    }

    public List<Comment> findAll(){
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    public void delete(Comment comment){
        em.remove(comment);
    }
}
