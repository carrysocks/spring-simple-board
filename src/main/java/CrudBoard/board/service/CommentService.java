package CrudBoard.board.service;

import CrudBoard.board.domain.Comment;
import CrudBoard.board.domain.Member;
import CrudBoard.board.repository.CommentRepository;
import CrudBoard.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    /** 댓글 작성 **/
    @Transactional
    public Long create(Long memberId, String content){
        Member member = memberRepository.findOne(memberId);
        Comment comment = Comment.createComment(member, content);
        commentRepository.save(comment);
        return comment.getId();
    }

    /** 댓글 삭제 **/
    @Transactional
    public void deleteComment(Long commentId){
        Comment comment = commentRepository.findOne(commentId);
        commentRepository.delete(comment);
        comment.delete();
    }

    public List<Comment> findComments(){
        return commentRepository.findAll();
    }
}
