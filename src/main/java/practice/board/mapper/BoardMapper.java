package practice.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import practice.board.dto.BoardDTO;
import practice.board.dto.BoardFileDTO;

import java.util.List;

@Mapper
public interface BoardMapper {

    BoardDTO save(BoardDTO boardDTO);

    public void list();

    List<BoardDTO> findAll();

    public void updateHits(Long id);

    BoardDTO findById(Long id);

    public void update(BoardDTO boardDTO);

    void delete(Long id);

    void saveFile(BoardFileDTO boardFileDTO);

    BoardFileDTO findFIle(Long id);
}
