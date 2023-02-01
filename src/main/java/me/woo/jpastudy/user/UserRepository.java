package me.woo.jpastudy.user;

import java.util.Objects;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserRepository {

	@PersistenceContext // entityManager 주입 받기
	EntityManager entityManager;

	public User insertUser(User user) {
		entityManager.persist(user);
		return user;
	}

	public User updateUser(User user) {
		if (Objects.nonNull(user.getId())) {
			return entityManager.merge(user);
		} else {
			throw new EntityNotFoundException();
		}
	}

	public User selectUser(long id) {
		return entityManager.find(User.class, id);
	}
}