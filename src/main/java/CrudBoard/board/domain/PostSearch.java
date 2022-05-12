package CrudBoard.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostSearch {
    
    //입력 받는 값
    private String inputString;
    //검색 조건[title, content, username]
    //private SearchStatus searchStatus;
    private String searchStatus;
}
