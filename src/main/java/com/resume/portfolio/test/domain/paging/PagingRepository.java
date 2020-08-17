package com.resume.portfolio.test.domain.paging;

import com.resume.portfolio.test.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagingRepository extends JpaRepository<Board, Long> {
}
