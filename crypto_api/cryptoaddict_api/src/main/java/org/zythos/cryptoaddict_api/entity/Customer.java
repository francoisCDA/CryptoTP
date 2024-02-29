package org.zythos.cryptoaddict_api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table(value = "customer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private UUID id;
    @Column(value = "firstname")
    private String firstName;
    @Column(value = "lastName")
    private String lastName;
    @Column(value = "email")
    private String email;
    @Column(value = "password")
    private String password;
    @Column(value = "piggy_bank")
    private Double piggyBank;
}
