package org.zythos.crypto_app_api.dto;

import lombok.Builder;
import lombok.Data;

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
