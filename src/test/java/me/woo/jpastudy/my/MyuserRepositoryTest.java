package me.woo.jpastudy.my;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import me.woo.jpastudy.user.User;
import me.woo.jpastudy.user.UserRepository;

@SpringBootTest
@Transactional  // 실무에서 사용시 주의해야합니다. (테스트 대상 환경에 영향을 줌)
@Rollback(value = false)
public class MyuserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Test
	void myUserRepositoryDeleteTest() {
		// given
		var newUser = User.builder().username("new").password("pass").build();
		userRepository.save(newUser);

		// when
		userRepository.delete(newUser);

		// then
	}

	@Test
	void myUserRepositoryFindUserNameAll() {
		// given
		var newUser = User.builder().username("new_user1").password("pass").build();
		var newUser2 = User.builder().username("new_user2").password("pass").build();
		userRepository.save(newUser);
		userRepository.save(newUser2);

		// when
		var userNameList = userRepository.findNameAll();

		// then

		Assertions.assertThat(
			userNameList.containsAll(List.of(newUser.getUsername(), newUser2.getUsername())));
	}
}
