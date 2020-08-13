package com.resume.portfolio.test.web.controller.board;

import com.resume.portfolio.test.domain.board.Board;
import com.resume.portfolio.test.domain.board.BoardRepository;
import com.resume.portfolio.test.web.dto.board.BoardSaveRequestDto;
import com.resume.portfolio.test.web.dto.board.BoardUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private BoardRepository boardRepository;

    @After
    public void tearDown() throws Exception {
        boardRepository.deleteAll();
    }

    @Test
    public void Board_등록된다() throws Exception {
        // given
        String title = "title";
        String content = "content";
        BoardSaveRequestDto boardSaveRequestDto = BoardSaveRequestDto.builder()
                                                    .title(title)
                                                    .content(content)
                                                    .author("author")
                                                    .build();

        String url = "http://localhost:" +  port + "board/api/save";

        // when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, boardSaveRequestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Board> all = boardRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Board_수정된다() throws Exception {
        // given
        Board savedBoard = boardRepository.save(Board.builder()
                                        .title("title")
                                        .content("content")
                                        .author("author")
                                        .build());

        Long updateId = savedBoard.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        BoardUpdateRequestDto updateRequestDto = BoardUpdateRequestDto.builder()
                                                    .title(expectedTitle)
                                                    .content(expectedContent)
                                                    .build();

        String url = "http://localhost:" + port + "/board/api/update/" + updateId;

        HttpEntity<BoardUpdateRequestDto> updateRequestEntity = new HttpEntity<>(updateRequestDto);

        // when
        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, updateRequestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Board> all = boardRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }



}
