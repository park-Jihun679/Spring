package org.scoula.board.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class BoardServiceImplTest {

    @Autowired
    private BoardService service;

    @Test
    void getList() {
        List<BoardDTO> list = service.getList();
        for (BoardDTO dto : list) {
            log.info(dto);
        }
    }

    @Test
    void get() {
        BoardDTO board = service.get(1L);
        log.info(board);

        assertEquals("테스트 제목1", board.getTitle());

        assertThrows(NoSuchElementException.class, () -> service.get(11L));
    }

    @Test
    void create() {
        BoardDTO createBoard = BoardDTO.builder()
            .title("crate 테스트 제목")
            .content("crate 테스트 내용")
            .writer("테스트 작성자")
            .build();

        service.create(createBoard);

        getList();
    }

    @Test
    void update() {
        BoardDTO updateBoard = BoardDTO.builder()
            .no(4L)
            .title("수정 테스트22")
            .content("수정 테스트 내용22")
            .writer("user022")
            .build();

        boolean result = service.update(updateBoard);
        BoardDTO resultBoard = service.get(4L);

        assertTrue(result);
        assertNotNull(resultBoard.getUpdateDate());

        getList();
    }

    @Test
    void delete() {
        List<BoardDTO> beforeList = service.getList();

        service.delete(9L);

        List<BoardDTO> afterList = service.getList();

        assertEquals(beforeList.size() - 1, afterList.size());
    }

}