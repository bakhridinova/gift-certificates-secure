package com.epam.esm.security.authentication.basic;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;

/**
 * class representing basic authentication
 *
 * @author bakhridinova
 */

@Data
public class CustomBasicAuthentication implements Authentication {
    private Collection<? extends GrantedAuthority> authorities;
    private final boolean authenticated;
    private final String username;
    private final String password;

    public CustomBasicAuthentication(boolean authenticated, String encodedCredentials) {
        this.authenticated = authenticated;

        byte[] credDecoded = Base64.getDecoder().decode(encodedCredentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        final String[] values = credentials.split(":", 2);
        username = values[0];
        password = values[1];
    }

    public CustomBasicAuthentication(boolean authenticated, String username, String password) {
        this.authenticated = authenticated;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
