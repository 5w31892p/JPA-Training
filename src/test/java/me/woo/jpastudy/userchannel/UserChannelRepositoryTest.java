package me.woo.jpastudy.userchannel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import me.woo.jpastudy.channel.ChannelRepository;
import me.woo.jpastudy.common.PageDTO;
import me.woo.jpastudy.user.User;
import me.woo.jpastudy.user.UserRepository;

@SpringBootTest
@Transactional
@Rollback(value = false) // 롤백 햐지 않도록
class UserChannelRepositoryTest {

	// @Autowired
	// private UserChannelRepository userChannelRepository;
	// cascade = CascadeType.ALL, orphanRemoval = true 설정으로 노필요
	// 아예 리포지토리 파일이 없어도 상관이 없음 -> UserChannelRepository 삭제

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ChannelRepository channelRepository;

	/*@Test
	@DisplayName("채널에 유저 가입 테스트")
	void userjoinChannelTest() {
		// given
		var newChannel = Channel.builder().name("new-channel").build();
		var newUser = User.builder().username("new-user").password("new-pass").build();
		var newUserChannel = newChannel.joinUser(newUser);

		// when
		var savedChannel = channelRepository.save(newChannel);
		var savedUser = userRepository.findByUsername(newUser.getUsername());
		// var savedUserChannel = userChannelRepository.insertUserChannel(newUserChannel);
		// cascade = CascadeType.ALL, orphanRemoval = true 설정으로 노필요

		// then
		var foundChannel = channelRepository.findById(savedChannel.getId());
		assert foundChannel.get().getUserChannels().stream()
			.map(UserChannel::getChannel)
			.map(Channel::getName)
			.anyMatch(name -> name.equals(newChannel.getName()));
	}

	@Test
	@DisplayName("채널에 유저가입 테스트 (with Cascade)")
	void userJoinchannelWithCascadeTest() {
		// given
		var newUser = User.builder().username("new_user").password("pass").build();
		var newChannel = Channel.builder().name("new_group").build();
		var savedUser = userRepository.save(newUser);

		// when
		newChannel.joinUser(savedUser);
		channelRepository.save(newChannel);

		// then
		var foundUser = userRepository.findById(savedUser.getId());
		assert foundUser.get().getUserChannel().stream()
			.map(UserChannel::getChannel)
			.map(Channel::getName)
			.anyMatch(name -> name.equals(newChannel.getName()));
	}*/
	@Test
	void userCustomFieldSortingTest() {
		// given
		var newUser1 = User.builder().username("new_user").password("new-pass1").build();
		var newUser2 = User.builder().username("new_user").password("new-pass2").build();
		userRepository.save(newUser1);
		userRepository.save(newUser2);

		// when
		var users = userRepository.findByUsernameWithCustomField("new_user", Sort.by("customField"));

		// then
		assert users.stream().map(User::getPassword)
			.anyMatch(password -> password.equals(newUser1.getPassword()));

		// when
		users = userRepository.findByUsernameWithCustomField("new_user",
			Sort.by("customField").descending());

		// then
		assert users.get(0).getPassword().equals(newUser2.getPassword());

		var newUser3 = User.builder().username("new_user").password("3").build();
		userRepository.save(newUser3);

		// when
		users = userRepository.findByUsername("new_user",
			JpaSort.unsafe("LENGTH(password)"));

		// then
		assert users.get(0).getPassword().equals(newUser3.getPassword());
	}

	@Test
	void pageDTOTest() {
		// given
		var newUser1 = User.builder().username("new_user").password("new-pass1").build();
		var newUser2 = User.builder().username("new_user").password("new-pass2").build();
		var newUser3 = User.builder().username("new_user").password("new-pass3").build();
		userRepository.save(newUser1);
		userRepository.save(newUser2);
		userRepository.save(newUser3);

		var pageDTO = new PageDTO(1, 2, "password");
		// when
		var page = userRepository.findAll(pageDTO.toPageable());

		// then
		assert page.getContent().size() == 2;
	}
}