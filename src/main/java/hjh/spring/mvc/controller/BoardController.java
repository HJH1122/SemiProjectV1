package hjh.spring.mvc.controller;



import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import hjh.spring.mvc.service.BoardService;
import hjh.spring.mvc.vo.BoardVO;

@Controller
public class BoardController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	/* 페이징 처리 */
	/* 페이지당 게시물 수perPage : 25 */
	/* 총 페이지 수 : 전체 게시물 수 / 페이지당 게시물 수 */
	/* 총 페이지 수pages : ceil(getTotalPage / perPage) */
	/* 2페이지 = 50 / 25 , 3페이지 = 51 / 25 */
	
	/* 페이지별 읽어올 게시글 범위 */
	/* 총 게시글이 55건 이라 할때 */
	/* 1page : 1번째 ~ 25번째 게시글 읽어옴 */
	/* 2page : 26번째 ~ 50번째 게시글 읽어옴  */
	/* 3page : 51번째 ~ 75번째 게시글 읽어옴  */
	/* ipage : m번째 ~ n번째 게시글 읽어옴 */
	/* m = (i-1) * 25 + 1 */
	/*  */
	/*  */
	
	@Autowired
	private BoardService bsrv;
	
	@GetMapping("/list")
	public String list(Model m, String cpg) {
		
		int perPage = 25;
		int snum = (Integer.parseInt(cpg) - 1) * perPage;
		
		m.addAttribute("bdlist", bsrv.readBoard(snum));
		
		return "board/list";
		
	}
	@GetMapping("/view")
	public ModelAndView view(ModelAndView mv, String bno) {
		
		mv.setViewName("board/view");
		mv.addObject("bd", bsrv.readOneBoard(bno));
		
		
		return mv;
		
	}
	//로그인 안했다면 -> redirect:/login
	//로그인 했다면 -> board/write
	@GetMapping("/write")
	public String write(HttpSession sess) {
		String returnPage = "board/write";
		
		if(sess.getAttribute("m")== null)
			returnPage = "redirect:/login";
		
		
		return returnPage;
		
	}
	@PostMapping("/write")
	public String writeok(BoardVO bvo) {
		
		bsrv.newBoard(bvo);
		
		return "redirect:/list";
		
	}
	
}
