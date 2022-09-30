package hjh.spring.mvc.service;

import hjh.spring.mvc.vo.MemberVO;

public interface MemberService {

	boolean newMember(MemberVO mvo);

	MemberVO readOneMember();

}
