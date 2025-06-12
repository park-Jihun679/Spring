package org.scoula.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.scoula.domain.BoardVO;

public interface BoardMapper {

    List<BoardVO> selectAllByMapper();

    @Select("""
        select *
        from tbl_board
        """)
    List<BoardVO> selectAllByAnnotation();


}
