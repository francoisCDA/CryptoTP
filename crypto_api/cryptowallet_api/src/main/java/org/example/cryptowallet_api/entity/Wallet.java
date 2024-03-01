package org.example.cryptowallet_api.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Wallet {

    private List<Transaction> transactions;
}
