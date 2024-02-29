package org.example.cryptowallet_api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDTO {


    private LocalDateTime transactionDate;
    private String cryptoCurrencyName;
    private Double quantity;
    private Double price;
}
