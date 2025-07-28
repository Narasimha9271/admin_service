package com.bank.admin_service.service;

import com.bank.admin_service.dto.AdminLoginDTO;
import com.bank.admin_service.dto.AdminRegisterDTO;
import com.bank.admin_service.dto.JwtResponseDTO;

public interface AdminService {
    JwtResponseDTO register(AdminRegisterDTO dto);
    JwtResponseDTO login(AdminLoginDTO dto);
}