package com.resume.portfolio.test.web.controller.board;

import com.resume.portfolio.test.service.board.BoardService;
import com.resume.portfolio.test.web.dto.board.BoardResponseDto;
import com.resume.portfolio.test.web.dto.board.BoardSaveRequestDto;
import com.resume.portfolio.test.web.dto.board.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public Long save(@RequestBody BoardSaveRequestDto boardSaveRequestDto) {

        return boardService.save(boardSaveRequestDto);
    }

    @PutMapping("/api/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {

        return boardService.update(id, boardUpdateRequestDto);
    }

    @GetMapping("/api/board/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {

        return boardService.findById(id);
    }

}
