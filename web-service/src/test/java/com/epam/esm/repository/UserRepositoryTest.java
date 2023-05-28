package com.epam.esm.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private static final String BCRYPT_SALT = "^$2a$10$";
    private static final String RANDOM_REGEX = "[a-z]";

    @Test
    @Order(1)
    void contextLoads() {
        assertNotNull(userRepository);
    }

    @Test
    @Order(2)
    void findByPasswordRegex_ShouldEmptyList_IfPasswordsDoNotMatch() {
        assertEquals(10, userRepository.findByPasswordNotMatchingRegex(
                BCRYPT_SALT).size());
    }

    @Test
    @Order(3)
    void findByPasswordRegex_ShouldCorrectList_IfPasswordsMatch() {
        assertEquals(0, userRepository
                .findByPasswordNotMatchingRegex(RANDOM_REGEX).size());
    }
}