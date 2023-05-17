package com.epam.esm.repository;

import com.epam.esm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true,
            value = "select * from users where not password ~ :regex ;")
    List<User> findByPasswordMatchingRegex(String regex);

    @Query(nativeQuery = true,
            value = "select * from users where username = :username ;")
    Optional<User> findByUsername(String username);
}
