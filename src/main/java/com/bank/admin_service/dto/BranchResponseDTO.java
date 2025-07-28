package com.bank.admin_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchResponseDTO {
    private Long id;
    private String name;
    private String address;
    private Long bankId;
    private String bankName;
}
