package com.ams.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Keep information about token.
 *
 * @author Alexey Mironov
 */
public class SecurityToken implements Serializable {
    /**
     * The token.
     */
    private String token;
    /**
     * The username.
     */
    private String username;
    /**
     * The created at.
     */
    private Date createdAt;
    /**
     * User rights map.
     */
    private final List<GrantedAuthority> rights = new ArrayList<>();

    public String getToken() {
        return token;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public List<GrantedAuthority> getRight() {
        return new ArrayList<>(rights);
    }

    public static SecurityToken of(Authentication authentication) {
        SecurityToken securityToken = new SecurityToken();
        securityToken.createdAt = new Date();
        securityToken.token = UUID.randomUUID().toString();
        securityToken.rights.addAll(authentication.getAuthorities());
        securityToken.username = authentication.getName();
        return securityToken;
    }

    public Authentication toAuthentication() {
        return new BearerToken(token, rights);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
