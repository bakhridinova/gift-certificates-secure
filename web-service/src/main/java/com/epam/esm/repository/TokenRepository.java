package com.epam.esm.repository;

import com.epam.esm.entity.Token;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
  Page<Token> findByUserId(Long user_id, Pageable pageable);
  List<Token> findByCreatedAtBefore(LocalDateTime createdAt);
  Optional<Token> findByAccessToken(String accessToken);
}
