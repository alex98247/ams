package com.ams.rest;

import com.ams.ro.AuthenticationRequest;
import com.ams.ro.LogoutRO;
import com.ams.ro.TokenRO;
import com.ams.security.SecurityToken;
import com.ams.security.SecurityUtils;
import com.ams.service.SecurityService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthenticationRest {

    private final SecurityService securityService;

    public AuthenticationRest(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("login")
    public ResponseEntity<TokenRO> login(@RequestBody AuthenticationRequest request) {
        SecurityToken token = securityService.login(request.getLogin(), request.getPassword());
        return ResponseEntity.ok().body(TokenRO.of(token));
    }

    @PostMapping("logout")
    public ResponseEntity<LogoutRO> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String tokenHeader) {
        if (SecurityUtils.validateTokenHeader(tokenHeader)) {
            ResponseEntity.ok().body(new LogoutRO("Token validation failed", false));
        }
        String token = SecurityUtils.parseToken(tokenHeader);
        securityService.logout(token);
        return ResponseEntity.ok().body(new LogoutRO(true));
    }
}
