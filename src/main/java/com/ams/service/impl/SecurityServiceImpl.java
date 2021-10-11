package com.ams.service.impl;

import com.ams.configuration.CoreConfigurationConstants;
import com.ams.security.SecurityToken;
import com.ams.service.SecurityService;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.NearCacheConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Responsible for authentication and authorization
 *
 * @author Alexey Mironov
 */
@Service
public class SecurityServiceImpl implements SecurityService {
    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);
    /**
     * The authentication manager.
     */
    private final AuthenticationManager authenticationManager;
    /**
     * Token validity time in seconds.
     */
    @Value("${spring.security.authentication.jwt.validity}")
    private int tokenValidityInSeconds;
    /**
     * The Hazelcast instance.
     */
    private final HazelcastInstance cache;
    /**
     * Token cache map.
     */
    private IMap<String, SecurityToken> tokenCache;

    public SecurityServiceImpl(
            @Qualifier("hazelcastInstance") HazelcastInstance hazelcast,
            AuthenticationManager authenticationManager) {
        this.cache = hazelcast;
        this.authenticationManager = authenticationManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityToken login(String login, String password) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)
        );
        SecurityToken token = SecurityToken.of(authentication);
        tokenCache.put(token.getToken(), token);
        return token;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean authenticate(String token) {
        SecurityToken securityToken = tokenCache.get(token);
        if (securityToken != null) {
            Authentication authentication = securityToken.toAuthentication();
            authentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean logout(String tokenString) {
        SecurityToken token = tokenCache.get(tokenString);
        boolean isTokenValid = token != null;
        if (isTokenValid) {
            tokenCache.delete(tokenString);
        }
        return isTokenValid;
    }

    private boolean configureCache() {
        try {
            final MapConfig tokensMapConfig = new MapConfig(CoreConfigurationConstants.CORE_SECURITY_TOKENS_MAP_NAME)
                    .setBackupCount(1)
                    .setReadBackupData(true)
                    .setMaxIdleSeconds(tokenValidityInSeconds)
                    .setMergePolicy("com.hazelcast.spi.merge.PassThroughMergePolicy")
                    .setNearCacheConfig(new NearCacheConfig()
                            .setCacheLocalEntries(true)
                            .setInMemoryFormat(InMemoryFormat.OBJECT)
                            .setMaxIdleSeconds(tokenValidityInSeconds)
                            .setInvalidateOnChange(true));

            cache.getConfig().addMapConfig(tokensMapConfig);
        } catch (Exception e) {
            LOGGER.warn("Cannot set up security tokens map configuration. Exception caught.", e);
            return false;
        }

        return true;
    }

    @PostConstruct
    private void init() {
        configureCache();
        this.tokenCache = cache.getMap(CoreConfigurationConstants.CORE_SECURITY_TOKENS_MAP_NAME);
    }
}
