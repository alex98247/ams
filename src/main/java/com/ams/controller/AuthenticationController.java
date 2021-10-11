package com.ams.controller;

import com.ams.ro.AuthenticationRequest;
import com.ams.ro.TokenRO;
import com.ams.security.SecurityToken;
import com.ams.service.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthenticationController {

    private final SecurityService securityService;

    public AuthenticationController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("login")
    public ResponseEntity<TokenRO> login(AuthenticationRequest request) {
        SecurityToken token = securityService.login("admin", "admin");
        TokenRO tokenRO = new TokenRO();
        tokenRO.setToken(token);
        return ResponseEntity.ok().body(tokenRO);
    }

    @GetMapping("logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok().build();
    }
}
