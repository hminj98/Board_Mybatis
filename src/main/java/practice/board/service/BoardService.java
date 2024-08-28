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
        if(boardDTO.getBoardFile().get(0).isEmpty()){
            // 첨부 파일 없음.
            boardDTO.setFileAttached(0);
            boardMapper.save(boardDTO);
        }
        else{
            // 첨부 파일 있음
            boardDTO.setFileAttached(1);
            boardMapper.save(boardDTO);
            BoardDTO saveBoard = boardMapper.findById(boardDTO.getId());

            // 파일만 따로 가져오기
            for(MultipartFile boardFile : boardDTO.getBoardFile()){
                log.info("boardFile : " + boardFile);

                // 파일 이름 가져오기
                String originalFilename = boardFile.getOriginalFilename();
                log.info("originalFilename : " + originalFilename);
                // 저장용 이름 만들기(예시)
                String storedFileName = System.currentTimeMillis() + "-" +originalFilename;
                log.info("storedFileName : " + storedFileName);

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

    public List<BoardFileDTO> findFile(Long id) {
        return boardMapper.findFile(id);
    }
}
