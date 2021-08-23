package hello.hellospring.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import hello.hellospring.domain.Member;

public class JdbcTemplateMemberRepository implements MemberRepository {

	private final JdbcTemplate jdbcTemplate;

	// @Autowired
	// 생성자가 하나일때는 Autowired annotation 생략이 가능하다.
	public JdbcTemplateMemberRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Member save(Member member) {
		// SimpleJdbcInsert 는
		// 테이블 네임과 PK만으로 쿼리없이 INSERT가 가능하다. 
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", member.getName());
		
		Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
		member.setId(key.longValue());
		return member;
	}
	
	@Override
	public Optional<Member> findById(Long id) {
		List<Member> result = jdbcTemplate.query("SELECT * FROM MEMBER WHERE ID = ?", memberRowMapper(), id);
		// Optional 로 변환
		return result.stream().findAny();
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = jdbcTemplate.query("SELECT * FROM MEMBER WHERE NAME = ?", memberRowMapper(), name);
		// Optional 로 변환
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		return jdbcTemplate.query("SELECT * FROM MEMBER", memberRowMapper());
	}

	private RowMapper<Member> memberRowMapper() {
		// JAVA 8 lambda style
		return (rs, rowNum) -> {

			Member member = new Member();
			member.setId(rs.getLong("id"));
			member.setName(rs.getString("name"));
			return member;
		};
		// 기존 코드
		/*
		 * return new RowMapper<Member>() {
		 * 
		 * @Override public Member mapRow(ResultSet rs, int rowNum) throws SQLException
		 * {
		 * 
		 * Member member = new Member(); member.setId(rs.getLong("id"));
		 * member.setName(rs.getString("name")); return member; } };
		 */
	}

}
