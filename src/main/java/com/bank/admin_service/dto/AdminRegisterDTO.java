package com.bank.admin_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminRegisterDTO {
    private String username;
    private String email;
    private String password;
}
