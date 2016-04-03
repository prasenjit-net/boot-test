package net.prasenjit.auth.repository;

import net.prasenjit.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by PRASEN on 4/3/2016.
 */
public interface UserRepository extends JpaRepository<User, String> {
}
