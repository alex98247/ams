package com.ams.security;

import com.ams.configuration.CoreConfigurationConstants;
import com.ams.service.SecurityService;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter request with invalid JWT token.
 *
 * @author Alexey Mironov
 */
@Component
public class BearerTokenFilter extends OncePerRequestFilter {
    /**
     * Token cache map.
     */
    private final IMap<String, SecurityToken> tokenCache;

    public BearerTokenFilter(@Qualifier("hazelcastInstance") HazelcastInstance hazelcast) {
        this.tokenCache = hazelcast.getMap(CoreConfigurationConstants.CORE_SECURITY_TOKENS_MAP_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String token = header.substring(7);
        SecurityToken securityToken = tokenCache.get(token);
        if (securityToken != null) {
            Authentication authentication = securityToken.toAuthentication();
            authentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
