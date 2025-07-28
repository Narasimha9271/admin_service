package com.bank.admin_service.controller;

import com.bank.admin_service.dto.AdminLoginDTO;
import com.bank.admin_service.dto.AdminRegisterDTO;
import com.bank.admin_service.dto.JwtResponseDTO;
import com.bank.admin_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponseDTO> register(@RequestBody AdminRegisterDTO dto) {
        return ResponseEntity.ok(adminService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody AdminLoginDTO dto) {
        return ResponseEntity.ok(adminService.login(dto));
    }
}
