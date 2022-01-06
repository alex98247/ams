package com.ams.ro;

import com.ams.security.SecurityToken;

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
        return tokenRO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
