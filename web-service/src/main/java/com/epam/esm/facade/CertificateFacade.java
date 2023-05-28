package com.epam.esm.facade;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificatePriceDto;
import com.epam.esm.util.filter.SearchFilter;
import org.springframework.data.domain.Page;

public interface CertificateFacade extends BaseFacade<CertificateDto> {
    Page<CertificateDto> findByFilterAndPage(SearchFilter searchFilter, int page, int size);
    CertificateDto updatePrice(CertificatePriceDto certificate);
    CertificateDto create(CertificateDto certificate);
    void deleteById(Long id);
}
