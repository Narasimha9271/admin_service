package com.bank.admin_service.service;

import com.bank.admin_service.dto.EmployeeRequestDTO;
import com.bank.admin_service.dto.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);
    EmployeeResponseDTO getEmployeeById(Long id);
    List<EmployeeResponseDTO> getAllEmployees();
    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto);
    void deleteEmployee(Long id);
}
