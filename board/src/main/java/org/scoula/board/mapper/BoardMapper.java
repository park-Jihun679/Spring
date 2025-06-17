package org.scoula.board.mapper;

import java.util.List;
import org.scoula.board.domain.BoardVO;

public interface BoardMapper {

    List<BoardVO> getList();

    BoardVO get(Long no);

    void create(BoardVO board);

    int update(BoardVO board);

    int delete(long no);
}
