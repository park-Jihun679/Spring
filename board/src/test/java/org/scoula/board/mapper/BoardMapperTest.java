package org.scoula.board.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.domain.BoardVO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class BoardMapperTest {

    @Autowired
    private BoardMapper mapper;

    @Test
    void getList() {
        List<BoardVO> list = mapper.getList();
        for(BoardVO vo : list) {
            log.info(vo);
        }
    }

    @Test
    void get() {
        BoardVO board = mapper.get(1L);
        log.info(board);
        assertEquals("테스트 제목1", board.getTitle());
    }

    @Test
    void create() {
        BoardVO createBoard = BoardVO.builder()
            .title("crate 테스트 제목")
            .content("crate 테스트 내용")
            .writer("테스트 작성자")
            .build();

        mapper.create(createBoard);

        getList();
    }

    @Test
    void update() {
        BoardVO updateBoard = BoardVO.builder()
            .no(3L)
            .title("수정 테스트")
            .content("수정 테스트 내용")
            .writer("user01")
            .build();

        int result = mapper.update(updateBoard);
        BoardVO resultBoard = mapper.get(3L);

        assertEquals(1, result);
        assertNotNull(resultBoard.getUpdateDate());
    }

    @Test
    void delete() {
        List<BoardVO> beforeList = mapper.getList();

        mapper.delete(8L);

        List<BoardVO> afterList = mapper.getList();

        assertEquals(beforeList.size() - 1, afterList.size());
    }
}