package practice.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import practice.board.dto.BoardDTO;
import practice.board.mapper.BoardMapper;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public void save(BoardDTO boardDTO) {
        boardMapper.save(boardDTO);
    }

    public List<BoardDTO> findAll() {
        log.info("findAll = {}" , boardMapper.findAll());
        return boardMapper.findAll();
    }

}
