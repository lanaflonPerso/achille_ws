package achille.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import achille.model.User;

public interface UserDAO extends CrudRepository<User,Integer>{
	@Query(" select u from User u " +
			" where u.username = ?1")
			Optional<User> findUserWithName(String username);

	boolean existsByUsername(String username);

	User findByUsername(String username);
}