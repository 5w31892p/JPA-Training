package me.woo.jpastudy.jdbc.template;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import me.woo.jpastudy.jdbc.vo.AccountVO;

@Repository
public class AccountTemplateDAO {

	private final JdbcTemplate jdbcTemplate;

	public AccountTemplateDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// SQL 쿼리
	private final String ACCOUNT_INSERT = "INSERT INTO account(ID, USERNAME, PASSWORD)"
		+ "VALUES((SELECT coalesce(MAX(ID), 0) + 1 FROM ACCOUNT A), ?, ?)";

	private final String ACCOUNT_GET = "SELECT * FROM account WHERE ID = ?";

	// crud 기능 메소드
	public Integer insertAccount(AccountVO vo) {
		// 기본 코드 (응답값은 생성된 갯수)
		// return jdbcTemplate.update(ACCOUNT_INSERT, vo.getUsername(), vo.getPassword());

		// id 값을 받아오기 위한 코드
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(con -> {
			var ps = con.prepareStatement(ACCOUNT_INSERT, new String[]{"id"});
			ps.setString(1, vo.getUsername());
			ps.setString(2, vo.getPassword());
			return ps;
		}, keyHolder);

		return (Integer) keyHolder.getKey();
	}

	//계정 조회
	public AccountVO selectAccount(Integer id) {
		return jdbcTemplate.queryForObject(ACCOUNT_GET, new AccountRowMapper(), id);
	}
}
