package CrudBoard.board.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Post extends Time{

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //글 작성 회원

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    //==생성 메서드==//
    public static Post createPost(Member member, String title, String content){
        Post post = new Post();
        post.setMember(member);
        post.setTitle(title);
        post.setContent(content);
        return post;
    }

    public void deletePost(){
        for(Comment comment : commentList){
            comment.delete();
        }
    }

}
