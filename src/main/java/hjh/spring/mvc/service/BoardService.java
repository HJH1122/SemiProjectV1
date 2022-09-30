package hjh.spring.mvc.service;

import java.util.List;

import hjh.spring.mvc.vo.BoardVO;

public interface BoardService {

	boolean newBoard(BoardVO bvo);

	List<BoardVO> readBoard();
	

}
