package com.bank.admin_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchRequestDTO {
    private String name;
    private String address;
    private Long bankId; // Bank to which this branch belongs
}
