package com.ams.ro;

import com.ams.security.SecurityToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Token REST response object.
 *
 * @author Alexey Mironov
 */
public class TokenRO {

    /**
     * The token.
     */
    private String token;

    private String username;

    private List<String> authorities;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static TokenRO of(SecurityToken token) {
        TokenRO tokenRO = new TokenRO();
        tokenRO.setToken(token.getToken());
        tokenRO.setUsername(token.getUsername());
        List<String> authorities = token.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        tokenRO.setAuthorities(authorities);
        return tokenRO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
