package me.woo.jpastudy.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import me.woo.jpastudy.user.User;

// JPA 삭제는 영속성 전이가 없으면 전이 후 삭제하기 때문에
// 다른 것 하지 않고 바로 삭제할 수 있는 기능 추가
public class MyUserRepositoryImple implements MyRepository<User> {

	@Autowired
	EntityManager entityManager;

	@Override
	public void delete(User user) {
		entityManager.remove(user);
	}

	@Override
	public List<String> findNameAll() {
		return entityManager.createQuery("SELECT u.username FROM User AS u", String.class).getResultList();
	}
}
