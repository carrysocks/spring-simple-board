package CrudBoard.board.web;

import CrudBoard.board.domain.Post;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class CommentForm {

    private Post post;

    @NotEmpty(message = "내용은 필수 입니다")
    private String content;
}
