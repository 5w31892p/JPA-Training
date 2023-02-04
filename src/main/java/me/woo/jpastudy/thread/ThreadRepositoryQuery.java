package me.woo.jpastudy.thread;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/*
ThreadRepository로 어노테이션 이동
ThreadRepository에서 ThreadRepositoryQuery를 상속 받은 후 아래 어노테이션을 달아주면 됨
@RepositoryDefinition(domainClass = Thread.class, idClass = Long.class)
*/

public interface ThreadRepositoryQuery {
	Page<Thread> search(ThreadSearchCond cond, Pageable pageable);
}
