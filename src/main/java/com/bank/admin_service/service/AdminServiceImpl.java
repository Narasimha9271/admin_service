package com.bank.admin_service.service;

import com.bank.admin_service.dto.AdminLoginDTO;
import com.bank.admin_service.dto.AdminRegisterDTO;
import com.bank.admin_service.dto.JwtResponseDTO;
import com.bank.admin_service.entity.Admin;
import com.bank.admin_service.repository.AdminRepository;
import com.bank.admin_service.security.TokenStore;
import com.bank.admin_service.service.AdminService;
import com.bank.admin_service.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;  // ✅ inject TokenStore

    @Override
    public JwtResponseDTO register(AdminRegisterDTO dto) {
        Admin admin = new Admin();
        admin.setEmail(dto.getEmail());
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        admin.setUsername(dto.getUsername());
        adminRepository.save(admin);

        String token = jwtService.generateToken(admin.getUsername());

        // ✅ store token for future outgoing calls
        tokenStore.setToken(token);

        return new JwtResponseDTO(token);
    }

    @Override
    public JwtResponseDTO login(AdminLoginDTO dto) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        if (auth.isAuthenticated()) {
            String token = jwtService.generateToken(dto.getUsername());

            // ✅ store token for future outgoing calls
            tokenStore.setToken(token);

            return new JwtResponseDTO(token);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
