package com.epam.esm.service;

import com.epam.esm.dto.TokenDto;
import org.springframework.data.domain.Page;

/**
 * interface holding business logic for tokens
 *
 * @author bakhridinova
 */

public interface TokenService extends BaseService<TokenDto> {

    Page<TokenDto> findByUserIdAndPage(Long userId, int page, int size);
}
