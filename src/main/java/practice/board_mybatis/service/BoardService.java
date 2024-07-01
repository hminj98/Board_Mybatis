package practice.board_mybatis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.board_mybatis.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
}
