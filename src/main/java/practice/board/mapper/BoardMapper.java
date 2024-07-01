package practice.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import practice.board.dto.BoardDTO;

import java.util.List;

@Mapper
public interface BoardMapper {

    public void save(BoardDTO boardDTO);

    public void list();

    List<BoardDTO> findAll();
}
