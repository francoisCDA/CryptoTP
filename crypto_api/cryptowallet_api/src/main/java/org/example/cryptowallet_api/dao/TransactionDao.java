package org.example.cryptowallet_api.dao;

import org.example.cryptowallet_api.dto.TransactionDTO;
import org.example.cryptowallet_api.entity.Transaction;
import org.example.cryptowallet_api.repository.TransactionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class TransactionDao {

    private final TransactionRepository walletRepository;

    public TransactionDao(TransactionRepository walletRepository){
        this.walletRepository = walletRepository;
    }

    public Flux<TransactionDTO> getCryptoWalletByPersonId(String idCustomer, String cryptoCurrencyName){
        return walletRepository.findAllByIdCustomerAndCryptoCurrencyName(idCustomer, cryptoCurrencyName)
                .map(wallet -> TransactionDTO.builder()
                        .transactionDate(wallet.getTransactionDate())
                        .cryptoCurrencyName(wallet.getCryptoCurrencyName())
                        .quantity(wallet.getQuantity())
                        .price(wallet.getPrice())
                        .build());
    }

    public Flux<TransactionDTO> getWalletsByCustomer(String idCustomer){
        return walletRepository.findGroupedTransactionsByIdCustomer(idCustomer)
                .map(wallet -> TransactionDTO.builder()
                        .transactionDate(wallet.getTransactionDate())
                        .cryptoCurrencyName(wallet.getCryptoCurrencyName())
                        .quantity(wallet.getQuantity())
                        .price(wallet.getPrice())
                        .build());
    }

    public Mono<Void> createNewTransaction(TransactionDTO transactionDTO, String idCustomer){
        return walletRepository.save(
                Transaction.builder()
                        .idCustomer(idCustomer)
                        .cryptoCurrencyName(transactionDTO.getCryptoCurrencyName())
                        .transactionDate(transactionDTO.getTransactionDate())
                        .quantity(transactionDTO.getQuantity())
                        .price(transactionDTO.getPrice())
                        .build()
        ).then();
    }


}




