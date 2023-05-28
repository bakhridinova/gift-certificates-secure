package com.epam.esm.security.authentication.bearer;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Class representing bearer authentication
 *
 * @author bakhridinova
 */

@Data
public class CustomBearerAuthentication implements Authentication {
    private boolean authenticated;
    private final String token;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomBearerAuthentication(boolean authenticated, String token) {
        this.authenticated = authenticated;
        this.token = token;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
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
    public String getName() {
        return null;
    }
}
