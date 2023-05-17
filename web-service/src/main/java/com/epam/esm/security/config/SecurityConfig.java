package com.epam.esm.security.config;

import com.epam.esm.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * security configuration
 *
 * @author bakhridinova
 */

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * bean adding custom authentication filter
     * to security filter chain
     *
     * @param httpSecurity HttpSecurity
     * @return instance of SecurityFilterChain
     * @throws Exception if error occurs
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
            throws Exception {

        return httpSecurity
                .httpBasic().and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/certificates", "/api/certificates/*").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/tags", "/api/tags/*").permitAll()
                .anyRequest().authenticated().and()
                .csrf().disable()
                .build();
    }


    /**
     * bean encrypting user passwords
     *
     * @return instance of BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * bean encrypting existing user passwords in database
     *
     * @param userRepository manipulating user data
     * @param passwordEncoder encoding passwords
     * @return instance of CommandLineRunner
     */
    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository,
                                               PasswordEncoder passwordEncoder) {

        return args -> userRepository
                .findByPasswordMatchingRegex(BCRYPT_PATTERN.pattern())
                .forEach(user -> {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    userRepository.save(user);
                });
    }
}
