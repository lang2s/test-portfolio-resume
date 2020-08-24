package com.resume.portfolio.test.service.board;

import com.resume.portfolio.test.domain.board.Board;
import com.resume.portfolio.test.domain.board.BoardRepository;
import com.resume.portfolio.test.web.dto.board.BoardListResponseDto;
import com.resume.portfolio.test.web.dto.board.BoardResponseDto;
import com.resume.portfolio.test.web.dto.board.BoardSaveRequestDto;
import com.resume.portfolio.test.web.dto.board.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto boardSaveRequestDto) {

        return boardRepository.save(boardSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));
        board.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));

        boardRepository.delete(board);
    }

    public BoardResponseDto findById(Long id) {

        Board entity = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));

        return new BoardResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BoardListResponseDto> findAllDesc(Pageable pageable) {

        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() -1);
        pageable = PageRequest.of(page, 10);

        return boardRepository.findAllDesc(pageable).stream()
                                    .map(BoardListResponseDto::new)
                                    .collect(Collectors.toList());
    }

//    @Transactional
//    public Page<BoardListResponseDto> getBoardListPaging(Pageable pageable) {
//
//        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() -1) // page는 index처럼 0부터 시작
//        pageable = PageRequest.of(page, 10);
//
//        return boardRepository.findAllDesc(pageable);
//    }


 }
