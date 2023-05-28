package com.epam.esm.facade;

import com.epam.esm.dto.Mappable;
import org.springframework.data.domain.Page;

/**
 * Wrapper interface for entity facades
 *
 * @author bakhridinova
 */

public interface BaseFacade<T extends Mappable> {
    Page<T> findAllByPage(int page, int size);
    T findById(Long id);
}
