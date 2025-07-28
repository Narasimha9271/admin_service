package com.bank.admin_service.security;

import org.springframework.stereotype.Component;

@Component
public class TokenStore {
    private String adminJwtToken;

    public String getToken() {
        return adminJwtToken;
    }

    public void setToken(String token) {
        this.adminJwtToken = token;
    }
}
