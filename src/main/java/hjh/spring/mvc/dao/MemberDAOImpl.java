package hjh.spring.mvc.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import hjh.spring.mvc.vo.MemberVO;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert;
	
	
	
	
	
	@Override
	public int insertMember(MemberVO mvo) {
		
		
		
		  String sql = "insert into member" + "(userid, userpw, name, email)" +
		  " values(?, ?, ?, ?)";
		  
		  Object[] params = new Object[] { mvo.getUserid(), mvo.getPasswd(),
		  mvo.getName(), mvo.getEmail() };
		  
		  return jdbcTemplate.update(sql, params);
		 
		
		
		
		
	}

	
	
}
