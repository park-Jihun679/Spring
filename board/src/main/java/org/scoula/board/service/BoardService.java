package org.scoula.board.service;

import java.util.List;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.springframework.stereotype.Service;

public interface BoardService {

    List<BoardDTO> getList();

    BoardDTO get(Long no);

    BoardDTO create(BoardDTO board);

    BoardDTO update(BoardDTO board);

    BoardDTO delete(Long no);


    // 첨부파일 관련 메서드 추가
    BoardAttachmentVO getAttachment(Long no);
    boolean deleteAttachment(Long no);
}