package com.bank.admin_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponseDTO {
    private Long id;
    private String type;
    private Double credit;
    private Double debit;
    private String timestamp;
    private Long customerId;
}
