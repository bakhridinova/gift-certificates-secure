package com.epam.esm.service;

import com.epam.esm.entity.Certificate;
import com.epam.esm.util.filter.SearchFilter;

import java.util.List;

/**
 * Interface holding business logic for certificates
 *
 * @author bakhridinova
 */

public interface CertificateService extends BaseService<Certificate> {

    List<Certificate> findByFilterAndPage(SearchFilter searchFilter, int page, int size);

    Certificate updatePrice(Certificate certificate);
}
