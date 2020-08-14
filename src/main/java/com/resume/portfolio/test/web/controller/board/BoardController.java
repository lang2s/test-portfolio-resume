package com.resume.portfolio.test.web.controller.board;

import com.resume.portfolio.test.service.board.BoardService;
import com.resume.portfolio.test.web.dto.board.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public String BoardList(Model model) {

        model.addAttribute("boardList", boardService.findAllDesc());

        return "/board/board-list";
    }

    @GetMapping("/board/save")
    public String boardSave() {

        return "/board/board-save";
    }
    @GetMapping("/board/detail/{id}")
    public String boardDetail(@PathVariable Long id, Model model) {

        BoardResponseDto dto = boardService.findById(id);
        model.addAttribute("board", dto);

        return "/board/board-detail";
    }
}
