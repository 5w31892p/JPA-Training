package me.woo.jpastudy.userchannel;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserChannelRepository {

	@PersistenceContext
	EntityManager entityManager;


	public UserChannel insertUserChannel(UserChannel userChannel) {
		entityManager.persist(userChannel);
		return userChannel;
	}

	public UserChannel selectUserChannel(Long id) {
		return entityManager.find(UserChannel.class, id);
	}
}
