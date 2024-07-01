package practice.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import practice.board.dto.BoardDTO;
import practice.board.service.BoardService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class BoardControllerTest {

    @Autowired // 빈으로 등록된 클래스에서 아래의 클래스명과 동일한 클래스를 찾아 주입
    BoardService boardService;
    BoardDTO boardDTO;



    @Test
    void findAll(){
        List<BoardDTO> findItem = boardService.findAll();


        // Stream을 사용하여 createdAt 필드만 추출
        List<Date> createdAtList = findItem.stream()
                .map(BoardDTO::getCreateAt)
                .collect(Collectors.toList());
        System.out.println("createdAtList = " + createdAtList);
        log.info("findItem ={}" , findItem);
    }
}