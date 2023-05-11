package com.epam.esm.repository;

import com.epam.esm.entity.Certificate;
import com.epam.esm.repository.custom.CustomizedCertificateRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long>, CustomizedCertificateRepository {

}
