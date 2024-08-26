package practice.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileDTO {
    private Long id;
    private Long boardId;
    private String originalFileName; // 기존 파일명
    private String storedFileName; // 저장 시 파일명
    
}
