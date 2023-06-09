package com.epam.esm.security.config;

import com.epam.esm.repository.UserRepository;
import com.epam.esm.security.authentication.filter.CustomAuthenticationFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.regex.Pattern;

/**
 * Security configuration
 *
 * @author bakhridinova
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private static final Pattern BCRYPT_PATTERN =
            Pattern.compile("\\A\\$2([ayb])?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");
    private static final String[] USER_AUTHENTICATION_POST_ENDPOINTS = {
            "/api/v1/users/register",
            "/api/v1/users/authenticate"
    };

    private static final String[] MAIN_ENTITY_GET_ENDPOINTS = {
            "/api/v1/certificates",
            "/api/v1/certificates/*",
            "/api/v1/tags",
            "/api/v1/tags/*",
            "/api/v1/tokens",
            "/api/v1/tokens/*"
    };

    /**
     * Bean encrypting user passwords
     *
     * @return instance of BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   CustomAuthenticationFilter authenticationFilter)
            throws Exception {
        return httpSecurity
                .csrf().disable().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, USER_AUTHENTICATION_POST_ENDPOINTS).permitAll()
                .requestMatchers(HttpMethod.GET, MAIN_ENTITY_GET_ENDPOINTS).permitAll()
                .anyRequest().authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Bean encrypting existing user passwords in database
     *
     * @param userRepository manipulating user data
     * @param passwordEncoder encoding passwords
     * @return instance of CommandLineRunner
     */
    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository,
                                               PasswordEncoder passwordEncoder) {

        return args -> userRepository
                .findByPasswordNotMatchingRegex(BCRYPT_PATTERN.pattern())
                .forEach(user -> {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    userRepository.save(user);
                });
    }
}
