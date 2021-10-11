package com.ams.security;

import com.ams.service.SecurityService;
import org.apache.commons.lang3.StringUtils;
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
     * The security service.
     */
    private final SecurityService securityService;

    public BearerTokenFilter(SecurityService securityService) {
        this.securityService = securityService;
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
        securityService.authenticate(token);
        chain.doFilter(request, response);
    }
}
