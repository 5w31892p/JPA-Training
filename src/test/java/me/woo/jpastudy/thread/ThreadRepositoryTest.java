package me.woo.jpastudy.thread;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import me.woo.jpastudy.channel.Channel;
import me.woo.jpastudy.channel.ChannelRepository;

@SpringBootTest
@Transactional
@Rollback(value = false) // 롤백 햐지 않도록
class ThreadRepositoryTest {

	@Autowired
	private ThreadRepository threadRepository;

	@Autowired
	private ChannelRepository channelRepository;

	@Test
	void insertSelectThreadTest() {
		// given
		var newChannel = Channel.builder().name("new-group").build();
		var newThread = Thread.builder().message("new-message").build();
		var newThread2 = Thread.builder().message("new-message2").build();
		newThread.setChannel(newChannel);
		newThread2.setChannel(newChannel);

		// when
		var savedChannel = channelRepository.save(newChannel);
		var savedThread = threadRepository.save(newThread);
		var savedThread2 = threadRepository.save(newThread2);

		// then
		// var foundThread = threadRepository.selectThread(savedThread.getId());
		// assert foundThread.getChannel().getName().equals(newChannel.getName());
		var foundChannel = channelRepository.findById(savedChannel.getId());
		assert foundChannel.get().getThreads().containsAll(Set.of(savedThread, savedThread2));
	}

	@Test
	void deleteThreadByOrphanRemovalTest() {
		// given
		var newChannel = Channel.builder().name("new-channel").build();
		var newThread = Thread.builder().message("new-message").build();
		var newThread2 = Thread.builder().message("new-message2").build();
		newThread.setChannel(newChannel);
		newThread2.setChannel(newChannel);
		var savedChannel = channelRepository.save(newChannel);
		var savedThread = threadRepository.save(newThread);
		var savedThread2 = threadRepository.save(newThread2);

		// when
		var foundChannel = channelRepository.findById(savedChannel.getId());
		foundChannel.get().getThreads().remove(savedThread);
	}

}
