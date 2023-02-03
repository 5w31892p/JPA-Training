package me.woo.jpastudy.channel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ChannelRepository extends JpaRepository<Channel, Long>, QuerydslPredicateExecutor<Channel> {
}

/*// Raw JPA
@Repository
public class ChannelRepository {

	@PersistenceContext // entitymanager bean으로 주입하는 어노테이션
	EntityManager entityManager;

	public Channel save(Channel channel) {
		entityManager.persist(channel);
		return channel;
	}

	public Channel findId(long id) {
		return entityManager.find(Channel.class, id);
	}
}*/
