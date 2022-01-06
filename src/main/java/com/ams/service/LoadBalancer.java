package com.ams.service;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Alexey Mironov
 */
public interface LoadBalancer {

    String getUser(GrantedAuthority authority);
}
