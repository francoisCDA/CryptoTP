package org.zythos.crypto_app_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.zythos.crypto_app_api.dto.TransactionDTO;
import reactor.core.publisher.Mono;

@Service
public class CryptoWalletService {

    private final WebClient webClient;


    public CryptoWalletService() {
        webClient = WebClient.builder().baseUrl("http://localhost:8083/").build();
    }

    public Mono<TransactionDTO[]> getCryptoWallet(String customerToken, String cryptoCurrencyName){
        return webClient.get().uri("api/wallets/"+customerToken+"/"+cryptoCurrencyName).retrieve().bodyToMono(TransactionDTO[].class);
    }

    public Mono<TransactionDTO[]> getCryptoWallets(String customerToken){
        return webClient.get().uri("api/wallets/"+customerToken).retrieve().bodyToMono(TransactionDTO[].class);
    }
}
