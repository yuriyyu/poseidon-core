package edu.mum.se.poseidon.core.repositories;

import edu.mum.se.poseidon.core.repositories.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yuriy Yugay on 10/11/2017.
 *
 * @author Yuriy Yugay
 */
public interface UserRepository
        extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
}
