package me.woo.jpastudy.thread;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
}

/* Raw JPA
@Repository
public class ThreadRepository {

	@PersistenceContext // entitymanager bean으로 주입하는 어노테이션
	EntityManager entityManager;

	public Thread insertThread(Thread Thread) {
		entityManager.persist(Thread);
		return Thread;
	}

	public Thread selectThread(long id) {
		return entityManager.find(Thread.class, id);
	}
}*/
