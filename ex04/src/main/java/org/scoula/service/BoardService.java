package org.scoula.service;

import java.util.List;
import org.scoula.domain.BoardVO;

public interface BoardService {

    List<BoardVO> getAllBoardByMapper();

    List<BoardVO> getAllBoardByAnnotation();
}
