package org.scoula.board.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void getList(Model model) {
        log.info("list 동작");

        List<BoardDTO> boardList = boardService.getList();
        for (BoardDTO dto : boardList) {
            log.info("boardList 데이터 =====> {} ", dto);
        }

        // boardDTO 리스트 모델에 담기
        model.addAttribute("list", boardList);
    }

    // 단건 조회
    @GetMapping("/get")
    public void get(@RequestParam("no") Long no, Model model) {
        BoardDTO board = boardService.get(no);

        log.info("단건 조회 요청 =====> {} ", board);

        model.addAttribute("board", board);
    }

    @PostMapping("/create")
    public String create(BoardDTO board) {
        log.info("생성 요청 createReq =====> {} ", board);

        boardService.create(board);

        return "redirect:/board/list";
    }

    @PostMapping("/update")
    public String update(BoardDTO board) {
        log.info("수정 요청 updateReq =====> {} ", board);
        boolean result = boardService.update(board);

        return "redirect:/board/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("no") long no) {
        log.info("/delete");
        boolean result = boardService.delete(no);

        return "redirect:/board/list";
    }
}
