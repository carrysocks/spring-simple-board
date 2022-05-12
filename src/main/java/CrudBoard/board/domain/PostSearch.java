package CrudBoard.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostSearch {

    private String title;
    private String content;
    private String username;
    private SearchStatus searchStatus;
}
