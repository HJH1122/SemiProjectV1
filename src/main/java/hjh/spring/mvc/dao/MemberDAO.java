package hjh.spring.mvc.dao;

import hjh.spring.mvc.vo.MemberVO;

public interface MemberDAO {

	int insertMember(MemberVO mvo);

	MemberVO selectOneMember();

}
