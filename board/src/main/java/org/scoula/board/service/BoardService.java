package org.scoula.board.service;

import java.util.List;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.springframework.stereotype.Service;

public interface BoardService {

    List<BoardDTO> getList();

    BoardDTO get(Long no);

    void create(BoardDTO board);

    boolean update(BoardDTO board);

    boolean delete(Long no);


    // 첨부파일 관련 메서드 추가
    BoardAttachmentVO getAttachment(Long no);
    boolean deleteAttachment(Long no);
}