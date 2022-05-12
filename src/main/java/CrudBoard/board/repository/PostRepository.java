package CrudBoard.board.repository;

import CrudBoard.board.domain.Member;
import CrudBoard.board.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    /**
     * 검색 기능 구현
     * @param input (입력 값)
     * @param status (검색 조건)
     */
    public List<Post> findPostsBySearch(String input, String status){
        String jpql = "select p from Post p join p.member m";

        if(StringUtils.hasLength(status)){
            if(status.equals("TITLE") && StringUtils.hasLength(input)){
                jpql += " where p.title like :input";
            }
            else if(status.equals("CONTENT") && StringUtils.hasLength(input)){
                jpql += " where p.content like :input";
            }
            else if(status.equals("USERNAME") && StringUtils.hasLength(input)){
                jpql += " where m.username like :input";
            }
        }

        TypedQuery<Post> query = em.createQuery(jpql, Post.class).setMaxResults(1000);
        if(StringUtils.hasLength(status) && StringUtils.hasLength(input)){
            query.setParameter("input", "%" + input + "%");
        }
        return query.getResultList();
    }

    public List<Post> findAllPosts(){
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    public void delete(Post post){
        em.remove(post);
    }



}
