package org.example.cryptowallet_api.service;

import org.example.cryptowallet_api.dao.TransactionDao;
import org.example.cryptowallet_api.dto.TransactionDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class WalletService {

    private final TransactionDao transactionDao;

    public WalletService(TransactionDao transactionDao){
        this.transactionDao = transactionDao;
    }

    public void createNewTransaction(String transactionDate, String cryptoCurrencyName, Double quantity, Double price, UUID personId){
        transactionDao.createNewTransaction(TransactionDTO.builder()
                        .transactionDate(LocalDateTime.parse(transactionDate))
                        .cryptoCurrencyName(cryptoCurrencyName)
                        .quantity(quantity)
                        .price(price)
                .build(), personId).then().subscribe();
    }

    public Flux<TransactionDTO> getCryptoWalletByPersonId(UUID personId, String cryptoCurrencyName){

        return transactionDao.getCryptoWalletByPersonId(personId, cryptoCurrencyName);

    }


}
