package org.scoula.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.scoula.domain.BoardVO;
import org.scoula.mapper.BoardMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    @Override
    public List<BoardVO> getAllBoardByMapper() {
        return boardMapper.selectAllByMapper();

    }

    @Override
    public List<BoardVO> getAllBoardByAnnotation() {
        return boardMapper.selectAllByAnnotation();
    }
}
