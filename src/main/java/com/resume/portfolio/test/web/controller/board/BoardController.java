package com.resume.portfolio.test.web.controller.board;

import com.resume.portfolio.test.config.auth.dto.SessionUser;
import com.resume.portfolio.test.service.board.BoardService;
import com.resume.portfolio.test.web.dto.board.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final HttpSession httpSession;

    @GetMapping("/board")
    public String BoardList(Model model) {

        model.addAttribute("boardList", boardService.findAllDesc());

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userList", user);
            System.out.println(">>>>>>userEmail: " + user.getEmail() + " <<<<<<<<<<< ");
        }

        return "/board/board-list";
    }

    @GetMapping("/board/detail/{id}")
    public String boardDetail(@PathVariable Long id, Model model) {

        BoardResponseDto dto = boardService.findById(id);
        model.addAttribute("board", dto);

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userList", user);
            System.out.println(">>>>>>userEmail: " + user.getEmail() + " <<<<<<<<<<< ");
        }

        return "/board/board-detail";
    }
}
