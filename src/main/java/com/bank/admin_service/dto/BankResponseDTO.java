package com.bank.admin_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankResponseDTO {
    private Long id;
    private String name;
    private String code;
}
