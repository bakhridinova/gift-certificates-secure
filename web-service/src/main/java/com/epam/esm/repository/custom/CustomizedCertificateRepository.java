package com.epam.esm.repository.custom;

import com.epam.esm.entity.Certificate;
import com.epam.esm.util.filter.SearchFilter;

import java.util.List;

/**
 * Custom repository holding functionality not supported by
 * {@link org.springframework.data.jpa.repository.JpaRepository JpaRepository},
 *
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.single-repository-behavior">Documentation</a>
 * @author bakhridinova
 */

public interface CustomizedCertificateRepository {
    List<Certificate> findByFilter(SearchFilter searchFilter);
}
