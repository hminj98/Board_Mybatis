package practice.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class BoardDTO {
    private Long id; // ID
    private String boardWriter; // 작성자
    private String boardPass; // 비밀번호
    private String boardTitle; // 제목
    private String boardContents; // 내용
    private int boardHits; // 조회수
    private String createdAt; // 작성일자
    private int fileAttached; // 파일 첨부 여부
    private List<MultipartFile> boardFile; // 첨부 파일
}
