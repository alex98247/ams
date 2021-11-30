package com.ams.rest.response;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static TokenRO of(SecurityToken token) {
        TokenRO tokenRO = new TokenRO();
        tokenRO.setToken(token.getToken());
        return tokenRO;
    }
}
