package me.woo.jpastudy.channel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}

/* Raw JPA
@Repository
public class ChannelRepository {

	@PersistenceContext // entitymanager bean으로 주입하는 어노테이션
	EntityManager entityManager;

	public Channel insertChannel(Channel channel) {
		entityManager.persist(channel);
		return channel;
	}

	public Channel selectChannel(long id) {
		return entityManager.find(Channel.class, id);
	}
}*/
