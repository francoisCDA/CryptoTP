package org.example.cryptowallet_api.repository;

import org.example.cryptowallet_api.entity.Transaction;
import org.example.cryptowallet_api.entity.Wallet;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, Long> {

    Flux<Transaction> findAllByIdCustomerAndCryptoCurrencyName(String idCustomer, String cryptoCurrencyName);

    @Aggregation("{ $match: { idCustomer: ?0 } } "
            + "{ $group: { _id: \"$cryptoCurrencyName\", transactions: { $push: \"$$ROOT\" } } }")
    Flux<Transaction> findGroupedTransactionsByIdCustomer(String idCustomer);
}
