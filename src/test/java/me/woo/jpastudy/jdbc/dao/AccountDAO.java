package me.woo.jpastudy.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.woo.jpastudy.jdbc.vo.AccountVO;

public class AccountDAO {

	// JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	private static final String url = "jdbc:postgresql://localhost:5432/messenger";
	private static final String username = "seowoo";
	private static final String password = "pass";

	// SQL 쿼리
	private final String ACCOUNT_INSERT = "INSERT INTO account(ID, USERNAME, PASSWORD)"
		+ "VALUES((SELECT coalesce(MAX(ID), 0) + 1 FROM ACCOUNT A), ?, ?)";

	private final String ACCOUNT_GET = "SELECT * FROM account WHERE ID = ?";

	// CRUD 기능 메소드
	public Integer insertAccount(AccountVO vo) {
		var id = -1;
		try {
			String[] returnId = {"id"};
			conn = DriverManager.getConnection(url, username, password);
			assert conn != null;
			stmt = conn.prepareStatement(ACCOUNT_INSERT, returnId);
			stmt.setString(1, vo.getUsername()); // 첫번째 인자 == 첫번째 물음표
			stmt.setString(2, vo.getPassword()); // 두번째 물음표
			stmt.executeUpdate();

			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					id = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public AccountVO selectAccount(Integer id) {
		AccountVO vo = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			assert conn != null;
			stmt = conn.prepareStatement(ACCOUNT_GET);
			stmt.setInt(1, id); // 첫번째 인자 == 첫번째 물음표 // 두번째 물음표
			var rs = stmt.executeQuery();

			if (rs.next()) {
				vo = new AccountVO();
				vo.setId(rs.getInt("ID"));
				vo.setUsername(rs.getString("USERNAME"));
				vo.setPassword(rs.getString("PASSWORD"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
}
