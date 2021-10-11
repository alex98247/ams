package com.ams.service;


import com.ams.security.SecurityToken;
import org.springframework.security.core.AuthenticationException;

/**
 * Responsible for authentication and authorization
 *
 * @author Alexey Mironov
 */
public interface SecurityService {

    /**
     * Authenticate user by login and password.
     *
     * @param login    - users login
     * @param password - users password
     * @return security token
     * @throws AuthenticationException if login or password not valid
     */
    SecurityToken login(String login, String password) throws AuthenticationException;

    /**
     * Validate token and set authentication.
     *
     * @param token - users token
     * @return true if token is valid or false otherwise.
     */
    boolean authenticate(String token);

    /**
     * Delete token from token cache.
     *
     * @param tokenString - users token
     * @return true if logout success or false otherwise.
     */
    boolean logout(String tokenString);
}
