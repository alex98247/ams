package com.ams.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Objects;

/**
 * BearerToken authentication.
 *
 * @author Alexey Mironov
 */
public class BearerToken extends AbstractAuthenticationToken {
    /**
     * The token.
     */
    private final String token;

    public BearerToken(String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getCredentials() {
        return token;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPrincipal() {
        return this.getCredentials();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        BearerToken bearerToken = (BearerToken) o;
        return Objects.equals(bearerToken.token, token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), token.hashCode());
    }
}
