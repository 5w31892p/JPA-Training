package me.woo.jpastudy.mybatis;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import me.woo.jpastudy.mybatis.configuration.DBConfiguration;
import me.woo.jpastudy.mybatis.mapper.AccountMapper;
import me.woo.jpastudy.mybatis.mapper.AccountMapperV2;
import me.woo.jpastudy.mybatis.vo.AccountMyBatisVO;

@SpringBootTest
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(DBConfiguration.class)
@Disabled
public class MyBatisTest {

	// Mapper 클래스를 받으려면 mapper.xml 빌드 해야하고, 그러려면 main 으로 옮겨서 해야함...
	@Autowired
	AccountMapper accountMapper;

	@Autowired
	AccountMapperV2 accountMapperV2;

	@Test
	@DisplayName("SQL Mapper - MyBatis 실습 (xml 사용)")
	void sqlMapper_MyBatisTest() {
		// given

		// when
		accountMapper.insertAccount(new AccountMyBatisVO("new user3", "new password3"));
		var account = accountMapper.selectAccount(1);

		// then
		assert !account.getUsername().isEmpty();
	}

	@Test
	@DisplayName("SQL Mapper - MyBatis V2 실습 (Annotation 사용)")
	void sqlMapper_MyBatisV2Test() {
		// given

		// when
		accountMapperV2.insertAccount(new AccountMyBatisVO("new user4", "new password4"));
		var account = accountMapperV2.selectAccount(1);

		// then
		assert !account.getUsername().isEmpty();
	}
}