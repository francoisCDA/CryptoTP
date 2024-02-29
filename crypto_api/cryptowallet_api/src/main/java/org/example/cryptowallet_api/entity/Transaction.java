package org.example.cryptowallet_api.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Document(collection = "transaction")
public class Transaction {

    @Id
    private String id;
    private String idCustomer; // FakeTokenByFrancois
    private String cryptoCurrencyName;
    private LocalDateTime transactionDate;
    private Double quantity;
    private Double price;
}
