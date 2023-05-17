package com.epam.esm.security.authentication.bearer;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * class representing bearer authentication
 *
 * @author bakhridinova
 */

@Data
@RequiredArgsConstructor
public class CustomBearerAuthentication implements Authentication {
    private final boolean authenticated;
    private final String token;
    private Collection<? extends GrantedAuthority> authorities;

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
