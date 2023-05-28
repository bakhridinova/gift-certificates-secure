package com.epam.esm.service;

import com.epam.esm.entity.Token;
import org.springframework.data.domain.Page;

/**
 * Interface holding business logic for tokens
 *
 * @author bakhridinova
 */

public interface TokenService extends BaseService<Token> {
    Page<Token> findByUserIdAndPage(Long userId, int page, int size);
}
