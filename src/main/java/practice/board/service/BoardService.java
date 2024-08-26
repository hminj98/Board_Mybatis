package practice.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import practice.board.dto.BoardDTO;
import practice.board.dto.BoardFileDTO;
import practice.board.mapper.BoardMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public void save(BoardDTO boardDTO) throws IOException {
        if(boardDTO.getBoardFile().isEmpty()){
            // 첨부 파일 없음.
            boardMapper.save(boardDTO);
        }else{
            // 첨부 파일 있음
            boardDTO.setFileAttached(1);

            // 게시글 저장 후 id값 활용을 위해 리턴
            BoardDTO saveBoard = boardMapper.save(boardDTO);

            // 파일만 따로 가져오기
            MultipartFile boardFile = boardDTO.getBoardFile();

            // 파일 이름 가져오기
            String originalFilename = boardFile.getOriginalFilename();

            // 저장용 이름 만들기(예시)
            String storedFileName = System.currentTimeMillis() + "-" +originalFilename;

            // BoardDTO 세팅
            BoardFileDTO boardFileDTO = new BoardFileDTO();
            boardFileDTO.setOriginalFileName(originalFilename);
            boardFileDTO.setStoredFileName(storedFileName);
            boardFileDTO.setBoardId(saveBoard.getId());

            // 파일 저장 경로 설정
            String savePath = "C:/Users/alswl/Desktop/" + storedFileName;
            boardFile.transferTo(new File(savePath));

            // DB 저장
            boardMapper.saveFile(boardFileDTO);
        }
    }

    public List<BoardDTO> findAll() {
        log.info("findAll = {}" , boardMapper.findAll());
        return boardMapper.findAll();
    }

    public void updateHits(Long id){
        boardMapper.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        return boardMapper.findById(id);
    }

    public void update(BoardDTO boardDTO) {
        boardMapper.update(boardDTO);
    }

    public void delete(Long id) {
        boardMapper.delete(id);
    }

    public BoardFileDTO findFile(Long id) {
        return boardMapper.findFIle(id);
    }
}
