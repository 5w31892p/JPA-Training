package me.woo.jpastudy.channel;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
}