package com.epam.esm.facade;

import com.epam.esm.dto.TokenDto;
import org.springframework.data.domain.Page;

public interface TokenFacade extends BaseFacade<TokenDto> {
    Page<TokenDto> findByUserIdAndPage(Long userId, int page, int size);
}
