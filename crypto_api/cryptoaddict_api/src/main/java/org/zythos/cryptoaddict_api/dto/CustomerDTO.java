package org.zythos.cryptoaddict_api.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

@Data
@Builder
public class CustomerDTO {

    private String customerToken;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Double piggyBank;
}
