package hjh.spring.mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import hjh.spring.mvc.vo.MemberVO;
import hjh.spring.mvc.vo.Zipcode;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert simpleInsert;
	private NamedParameterJdbcTemplate jdbcNameTemplate;
	
	//private RowMapper<MemberVO> memberMapper = BeanPropertyRowMapper.newInstance(MemberVO.class);
	private RowMapper<Zipcode> zipcodeMapper = BeanPropertyRowMapper.newInstance(Zipcode.class);
	
	public MemberDAOImpl(DataSource dataSource) {
		simpleInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("member")
				.usingColumns("userid","passwd","name","email");
		
		jdbcNameTemplate = new NamedParameterJdbcTemplate(dataSource);
		
	}
	

	@Override
	public int insertMember(MemberVO mvo) {
	      SqlParameterSource params = 
	            new BeanPropertySqlParameterSource(mvo);
	      
	      return simpleInsert.execute(params);
	   }


	@Override
	public MemberVO selectOneMember(String uid) {
		
		String sql = "select userid,name,email,regdate from member where userid = ?";
		
		Object[] param = {uid};
		
		
		
		RowMapper<MemberVO> memberMapper = (rs, num) -> {
			MemberVO m = new MemberVO();
			
			m.setUserid(rs.getString("userid"));
			m.setName(rs.getString("name"));
			m.setEmail(rs.getString("email"));
			m.setRegdate(rs.getString("regdate"));
			
			return m;
		};
		
		return jdbcTemplate.queryForObject(sql, param, memberMapper);
	}


	@Override
	public int selectOneMember(MemberVO m) {
		
		String sql = "select count(mno) cnt from member where userid = ? and passwd = ?";
		
		Object[] params = { m.getUserid(), m.getPasswd() };
		
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
		
	}


	@Override
	public int selectCountUserid(String uid) {
		String sql = "select count(mno) cnt from member where userid = ?";
		
		Object[] param = new Object[] { uid };
		
		
		return jdbcTemplate.queryForObject(sql, param, Integer.class);
	}


	@Override
	public List<Zipcode> selectZipcode(String dong) {
		String sql = "select * from zipcode_2013 where dong like :dong";
		
		Map<String, Object> param = new HashMap<>();
		param.put("dong", dong);
		
		return jdbcNameTemplate.query(sql, param, zipcodeMapper);
		
	}
	
	
	
}
