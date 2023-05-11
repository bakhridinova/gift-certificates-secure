package com.epam.esm.service;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.util.filter.SearchFilter;
import org.springframework.data.domain.Page;

/**
 * interface holding business logic for certificates
 *
 * @author bakhridinova
 */

public interface CertificateService extends BaseService<CertificateDto> {

    Page<CertificateDto> findByFilterAndPage(SearchFilter searchFilter, int page, int size);

    CertificateDto updatePriceById(Long id, CertificateDto certificate);
}
