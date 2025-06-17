package org.scoula.board.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper mapper;

    @Override
    public List<BoardDTO> getList() {
        return mapper.getList().stream().map(BoardDTO::of).toList();
    }

    @Override
    public BoardDTO get(Long no) {
        BoardVO vo = Optional.ofNullable(mapper.get(no))
            .orElseThrow(NoSuchElementException::new);
        return BoardDTO.of(vo);
    }

    @Override
    public void create(BoardDTO board) {
        BoardVO vo = board.toVo();
        mapper.create(vo);
        board.setNo(vo.getNo());
    }

    @Override
    public boolean update(BoardDTO board) {
        int result = mapper.update(board.toVo());
        return result == 1;
    }

    @Override
    public boolean delete(Long no) {
        int result = mapper.delete(no);
        return result == 1;
    }
}
