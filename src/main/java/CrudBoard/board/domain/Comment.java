package CrudBoard.board.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Comment extends Time{

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //==댓글 생성==//
    public static Comment createComment(Member member, String content) {
        Comment comment = new Comment();
        comment.setMember(member);
        comment.setContent(content);
        return comment;
    }

    public void delete(){

    }
}
