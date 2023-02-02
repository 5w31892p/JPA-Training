package me.woo.jpastudy.user;

import org.springframework.data.jpa.repository.JpaRepository;

import me.woo.jpastudy.my.MyRepository;

public interface UserRepository extends JpaRepository<User, Long>, MyRepository<User> {
}

/* repository 기능 제한 두번째 방법

public interface UserRepository extends MyRepository<User, Long> {
}

// 원래는 다른 interface에 있어야 함
@NoRepositoryBean
public interface MyRepository<User, ID extends Serializable> extends Repository<User, ID> {

	User save(User entity);

	List<User> findByUsername(String username);

}
*/


/* repository 기능 제한 첫번째 방법
@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository {

  Optional<User> findByUsername(String userName);
  // findByPassword를 막기 위해
}
*/



/* Raw JPA
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
}*/
