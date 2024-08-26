package practice.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import practice.board.dto.BoardDTO;

import java.util.List;

@Mapper
public interface BoardMapper {

    public void save(BoardDTO boardDTO);

    public void list();

    List<BoardDTO> findAll();

    public void updateHits(Long id);

    BoardDTO findById(Long id);

    public void update(BoardDTO boardDTO);

    void delete(Long id);

}
