package hjh.spring.mvc.dao;

import java.util.List;

import hjh.spring.mvc.vo.BoardVO;

public interface BoardDAO {

	int insertBoard(BoardVO bvo);

	List<BoardVO> selectBoard();

}
