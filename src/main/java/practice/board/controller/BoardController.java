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
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
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

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        log.info("boardDTO : "+ boardDTO);
        model.addAttribute("board", boardDTO);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(BoardDTO boardDTO, Model model){
        boardService.update(boardDTO);
        BoardDTO dto = boardService.findById(boardDTO.getId());
        log.info("dtp : "+ dto);
        model.addAttribute("board", dto);
        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        boardService.delete(id);
        return "redirect:/list";
    }

}
