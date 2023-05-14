package com.epam.esm.security.config;

import com.epam.esm.repository.UserRepository;
import com.epam.esm.security.authentication.filter.CustomAuthenticationFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * security configuration
 *
 * @author bakhridinova
 */

@Configuration
public class SecurityConfig {

    /**
     * bean adding custom authentication filter
     * to security filter chain
     *
     * @param httpSecurity HttpSecurity
     * @param authenticationFilter CustomAuthenticationFilter
     * @return instance of SecurityFilterChain
     * @throws Exception if error occurs
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   CustomAuthenticationFilter authenticationFilter)
            throws Exception {

        return httpSecurity
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests().anyRequest().authenticated()
                .and().build();
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

        return args -> userRepository.findAll().forEach(user -> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        });
    }
}
