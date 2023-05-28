package com.epam.esm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main application class
 *
 * @author bakhridinova
 */

@EnableScheduling
@SpringBootApplication
public class GiftCertificatesSecureApplication {

    public static void main(String[] args) {
        SpringApplication.run(GiftCertificatesSecureApplication.class, args);
    }
}