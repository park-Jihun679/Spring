package org.scoula.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.scoula.domain.BoardVO;
import org.scoula.service.BoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;

    @GetMapping("/api/mapper/boards")
    public List<BoardVO> getBoardList() {
        return boardService.getAllBoardByMapper();
    }

    @GetMapping("/api/annotation/boards")
    public List<BoardVO> getBoardList2() {
        return boardService.getAllBoardByAnnotation();
    }
}
