package com.resume.portfolio.test.web.controller.board;

import com.resume.portfolio.test.config.auth.LoginUser;
import com.resume.portfolio.test.config.auth.dto.SessionUser;
import com.resume.portfolio.test.service.board.BoardService;
import com.resume.portfolio.test.web.dto.board.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public String BoardList(Model model, @LoginUser SessionUser user, @PageableDefault Pageable pageable) {

        model.addAttribute("boardList", boardService.findAllDesc(pageable));

        if (user != null) {
            model.addAttribute("userList", user);
        }

        return "board/board-list";
    }

    @GetMapping("/board/detail/{id}")
    public String boardDetail(@PathVariable Long id, Model model, @LoginUser SessionUser user) {

        BoardResponseDto dto = boardService.findById(id);
        model.addAttribute("board", dto);

        if (user != null) {
            model.addAttribute("userList", user);
        }

        return "board/board-detail";
    }
}
