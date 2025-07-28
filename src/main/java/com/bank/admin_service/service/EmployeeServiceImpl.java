package com.bank.admin_service.service;

import com.bank.admin_service.dto.EmployeeRequestDTO;
import com.bank.admin_service.dto.EmployeeResponseDTO;
import com.bank.admin_service.entity.Branch;
import com.bank.admin_service.entity.Employee;
import com.bank.admin_service.repository.BranchRepository;
import com.bank.admin_service.repository.EmployeeRepository;
import com.bank.admin_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
        Branch branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        Employee employee = Employee.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .role(dto.getRole())
                .branch(branch)
                .build();

        employeeRepository.save(employee);

        return mapToDTO(employee);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return mapToDTO(emp);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Branch branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setRole(dto.getRole());
        employee.setBranch(branch);

        employeeRepository.save(employee);

        return mapToDTO(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeResponseDTO mapToDTO(Employee employee) {
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .role(employee.getRole())
                .branchId(employee.getBranch().getId())
                .branchName(employee.getBranch().getName())
                .build();
    }
}
