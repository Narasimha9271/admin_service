package com.bank.admin_service.controller;

import com.bank.admin_service.dto.EmployeeRequestDTO;
import com.bank.admin_service.dto.EmployeeResponseDTO;
import com.bank.admin_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeResponseDTO create(@RequestBody EmployeeRequestDTO dto) {
        return employeeService.createEmployee(dto);
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO get(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeResponseDTO> getAll() {
        return employeeService.getAllEmployees();
    }

    @PutMapping("/{id}")
    public EmployeeResponseDTO update(@PathVariable Long id, @RequestBody EmployeeRequestDTO dto) {
        return employeeService.updateEmployee(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
