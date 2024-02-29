package org.example.cryptowallet_api.repository;

import org.example.cryptowallet_api.entity.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, Long> {

    Flux<Transaction> findAllByPersonIdAndCryptoCurrencyName(UUID id, String cryptoCurrencyName);

}
