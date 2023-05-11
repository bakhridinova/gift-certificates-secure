package com.epam.esm.repository.custom.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.repository.custom.CustomizedCertificateRepository;
import com.epam.esm.util.filter.SearchFilter;
import com.speedment.jpastreamer.application.JPAStreamer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomizedCertificateRepositoryImpl implements CustomizedCertificateRepository {
    private final EntityManager entityManager;

    @Override
    public List<Certificate> findByFilterAndPage(SearchFilter searchFilter) {
        JPAStreamer jpaStreamer = JPAStreamer.of(entityManager.getEntityManagerFactory());
        return jpaStreamer.stream(Certificate.class)
                .filter(certificate -> certificate.getName().contains(searchFilter.name())
                        && certificate.getDescription().contains(searchFilter.description())
                        && new HashSet<>(certificate.getTags()).containsAll(searchFilter.tags()))
                .toList();
    }
}
