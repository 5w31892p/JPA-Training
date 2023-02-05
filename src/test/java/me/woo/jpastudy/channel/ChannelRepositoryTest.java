package me.woo.jpastudy.channel;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.core.types.Predicate;

import me.woo.jpastudy.common.RepositoryTest;

/*
@RepositoryTest를 만들어 아래 어노테이션들을 대체함

@SpringBootTest
@Transactional  // 실무에서 사용시 주의해야합니다. (테스트 대상 환경에 영향을 줌)
@Rollback(value = false)
*/

@RepositoryTest
class ChannelRepositoryTest {

	@Autowired
	private ChannelRepository channelRepository;

	@Test
	void insertSelectGroupTest() {
		// given
		var newChannel = Channel.builder().name("new-group").build();

		// when
		var savedChannel = channelRepository.save(newChannel);

		// then
		var foundChannel = channelRepository.findById(savedChannel.getId());
		assert foundChannel.get().getId().equals(savedChannel.getId());
	}

	@Test
	void queryDslTest() {
		// given
		var newChannel = Channel.builder().name("seowoo").build();
		channelRepository.save(newChannel);

		Predicate predicate = QChannel.channel
			.name.equalsIgnoreCase("SEOWOO");

		// when
		Optional<Channel> optional = channelRepository.findOne(predicate);

		// then
		assert optional.get().getName().equals(newChannel.getName());
	}
}

