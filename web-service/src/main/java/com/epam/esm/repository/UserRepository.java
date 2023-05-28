package com.epam.esm.repository;

import com.epam.esm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true,
            value = "select * from users where not password ~ :regex ;")
    List<User> findByPasswordNotMatchingRegex(String regex);

    Optional<User> findByUsername(String username);
}
