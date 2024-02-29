package org.example.cryptowallet_api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TransactionDTO {

    private LocalDateTime transactionDate;
    private String cryptoCurrencyName;
    private Double quantity;
    private Double price;
    private UUID idPerson;
}
