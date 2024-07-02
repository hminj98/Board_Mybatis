package practice.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import practice.board.dto.BoardDTO;
import practice.board.service.BoardService;

import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String save(){
        return "save";
    }

    @PostMapping("/save")
    public String save(BoardDTO boardDTO){
        boardService.save(boardDTO);
        log.info("boardDTO: {}", boardDTO);
        return "index";
    }

    @GetMapping("/list")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        System.out.println("boardDTOList = " + boardDTOList);
        return "list";
    }

//    @GetMapping("/{id}")
//    public String findById(@PathVariable("id") Long id , Model model){
//        // 조회수 + 1 , id 값으로 조회
//        boardService.updateHits(id);
//        BoardDTO boardDTO = boardService.findById(id);
//        model.addAttribute("board", boardDTO);
//        return "detail";
//    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id , Model model){
        // 조회수 + 1 , id 값으로 조회
        boardService.updateHits(id);
        BoardDTO findItem = boardService.findById(id);
        log.info("findById: {}", findItem);
        model.addAttribute("board", findItem);
        return "detail";
    }
    // 수정
    // 1. 정보표출
    // 2. 수정

}
