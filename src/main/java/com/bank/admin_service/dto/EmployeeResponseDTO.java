package com.bank.admin_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private Long branchId;
    private String branchName;
}
