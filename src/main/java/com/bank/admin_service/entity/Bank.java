package com.bank.admin_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private String location;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Branch> branches;
}
