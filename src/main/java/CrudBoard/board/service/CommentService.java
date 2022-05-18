package CrudBoard.board.service;

import CrudBoard.board.domain.Comment;
import CrudBoard.board.domain.Member;
import CrudBoard.board.domain.Post;
import CrudBoard.board.repository.CommentRepository;
import CrudBoard.board.repository.MemberRepository;
import CrudBoard.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    /** 댓글 작성 **/
    @Transactional
    public Long create(Long memberId, Long postId, String content){
        Member member = memberRepository.findOne(memberId);
        Post post = postRepository.findOne(postId);
        Comment comment = Comment.createComment(member, post, content);
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

    public List<Comment> findCommentsByPostId(Long postId){
        return commentRepository.findByPostId(postId);
    }

    public List<Comment> findComments(){
        return commentRepository.findAll();
    }
}
