package CrudBoard.board.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class CommentForm {

    @NotEmpty(message = "내용은 필수 입니다")
    private String content;
}
