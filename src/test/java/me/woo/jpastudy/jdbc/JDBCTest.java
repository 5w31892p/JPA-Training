package me.woo.jpastudy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.woo.jpastudy.jdbc.dao.AccountDAO;
import me.woo.jpastudy.jdbc.vo.AccountVO;

public class JDBCTest {
	// docker run -p 5432:5432 -e POSTGRES_PASSWORD=pass -e POSTGRES_USER=seowoo -e POSTGRES_DB=messenger --name postgres_boot -d postgres

	// docker exec -i -t postgres_boot bash
	// su - postgres
	// psql --username seowoo --dbname messenger
	// \list (데이터 베이스 조회)
	// \dt (테이블 조회)

	@Test
	@DisplayName("JDBC DB 연결 실습")
	void jdbcTest() {

		// 접속
		String url = "jdbc:postgresql://localhost:5432/messenger";
		String username = "seowoo";
		String password = "pass";
		// when
		// try{
		// 	Connection connection = DriverManager.getConnection(url, username, password);
		// 	String creatSql = "CREATE TABLE ACCOUNT (id SERIAL PRIMARY KEY, username varchar(255), password varchar(255))";
		// 	PreparedStatement statment = connection.prepareStatement(creatSql); // 변수로 받아오는 단축키 맥 command+option+v 윈도우 ctrl+alt+v
		// 	statment.execute(); // 쿼리 실행
		//
		// 	// 자원 해제 중요
		// 	statment.close();
		// 	connection.close();
		//
		// } catch (SQLException e) {
		// 	e.printStackTrace();
		// }

		// 자동 close

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String creatSql = "CREATE TABLE ACCOUNT (id SERIAL PRIMARY KEY, username varchar(255), password varchar(255))";
			try (PreparedStatement statment = connection.prepareStatement(
				creatSql)) {
				statment.execute(); // 쿼리 실행
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// then
		// DB 확인 select * from account;
	}

	@Test
	@DisplayName("JDBC 삽입/조회 실습")
	void jdbcInsertSelectTest() throws SQLException {
		// given
		String url = "jdbc:postgresql://localhost:5432/messenger";
		String username = "seowoo";
		String password = "pass";

		// when
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Connection created: " + connection);

			String insertSql = "INSERT INTO ACCOUNT (id, username, password) VALUES ((SELECT coalesce(MAX(ID), 0) + 1 FROM ACCOUNT A), 'user1', 'pass1')";
			try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
				statement.execute();
			}

			// then
			String selectSql = "SELECT * FROM ACCOUNT";
			try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
				var rs = statement.executeQuery();
				while (rs.next()) {
					System.out.printf("%d, %s, %s", rs.getInt("id"), rs.getString("username"),
						rs.getString("password"));
				}
			}
		}
	}
	@Test
	@DisplayName("JDBC DAO 삽입/조회 실습")
	void jdbcDAOInsertSelectTest() throws SQLException {
		// given
		AccountDAO accountDAO = new AccountDAO();

		// when
		var id = accountDAO.insertAccount(new AccountVO("new user", "new password"));

		// then
		var account = accountDAO.selectAccount(id);
		assert account.getUsername().equals("new user");
	}
}