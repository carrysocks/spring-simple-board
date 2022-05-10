package CrudBoard.board.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member{

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;
    private String username;
    private String password;

    private String refreshToken;//RefreshToken
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @OneToMany(mappedBy = "member")
    List<Post> postList = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    List<Comment> commentList = new ArrayList<>();

    public void addPost(Post post){
        postList.add(post);
    }

    public void addComment(Comment comment){
        commentList.add(comment);
    }

    //==생성 메서드==//
    public static Member createMember(String name,String username,String password){
        Member member = new Member();
        member.setName(name);
        member.setUsername(username);
        member.setPassword(password);
        member.setRole(MemberRole.USER);
        return member;
    }
}
