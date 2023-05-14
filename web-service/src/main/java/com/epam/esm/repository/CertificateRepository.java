package com.epam.esm.repository;

import com.epam.esm.entity.Certificate;
import com.epam.esm.repository.custom.CustomizedCertificateRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long>, CustomizedCertificateRepository {

}
