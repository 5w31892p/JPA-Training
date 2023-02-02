package me.woo.jpastudy.userchannel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import jakarta.transaction.Transactional;
import me.woo.jpastudy.channel.Channel;
import me.woo.jpastudy.channel.ChannelRepository;
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

	@Test
	@DisplayName("채널에 유저 가입 테스트")
	void userjoinChannelTest() {
		// given
		var newChannel = Channel.builder().name("new-channel").build();
		var newUser = User.builder().username("new-user").password("new-pass").build();
		var newUserChannel = newChannel.joinUser(newUser);

		// when
		var savedChannel = channelRepository.insertChannel(newChannel);
		var savedUser = userRepository.insertUser(newUser);
		// var savedUserChannel = userChannelRepository.insertUserChannel(newUserChannel);
		// cascade = CascadeType.ALL, orphanRemoval = true 설정으로 노필요

		// then
		var foundChannel = channelRepository.selectChannel(savedChannel.getId());
		assert foundChannel.getUserChannels().stream()
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
		var savedUser = userRepository.insertUser(newUser);

		// when
		newChannel.joinUser(savedUser);
		channelRepository.insertChannel(newChannel);

		// then
		var foundUser = userRepository.selectUser(savedUser.getId());
		assert foundUser.getUserChannel().stream()
			.map(UserChannel::getChannel)
			.map(Channel::getName)
			.anyMatch(name -> name.equals(newChannel.getName()));
	}
}