package com.epam.esm;

import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main application class
 *
 * @author bakhridinova
 */

@SpringBootApplication
@RequiredArgsConstructor
public class GiftCertificatesSecureApplication {
    private final CertificateRepository certificateRepository;
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(GiftCertificatesSecureApplication.class, args);
    }
}