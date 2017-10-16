package edu.mum.se.poseidon.core.repositories;

import edu.mum.se.poseidon.core.repositories.models.users.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Yuriy Yugay on 10/11/2017.
 *
 * @author Yuriy Yugay
 */
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.username= :username and u.deleted = false ")
	User findUserByUsername(@Param("username") String username);

	List<User> findAllByDeleted(boolean is_deleted);

	
}
