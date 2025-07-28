package com.bank.admin_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDTO {
    private String name;
    private String email;
    private String role; // e.g., MANAGER, CASHIER, etc.
    private Long branchId;
}
