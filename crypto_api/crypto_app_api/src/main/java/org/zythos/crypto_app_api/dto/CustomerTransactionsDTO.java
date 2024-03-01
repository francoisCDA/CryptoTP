package org.zythos.crypto_app_api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class CustomerTransactionsDTO {

    private String customerToken;
    private String firstName;
    private String lastName;
    private String email;
    private Double piggyBank;
    private List<TransactionDTO> transactionsList;

}
