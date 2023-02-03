package me.woo.jpastudy.thread;

import org.hibernate.mapping.List;
import org.springframework.data.domain.Page;

import me.woo.jpastudy.channel.Channel;
import me.woo.jpastudy.common.PageDTO;

public interface ThreadService {
	List<Thread> selectNotEmptyThreadList(Channel channel);

	Page<Thread> selectMentionedThreadList(Long userId, PageDTO pageDTO);

	Thread insert(Thread thread);
}
