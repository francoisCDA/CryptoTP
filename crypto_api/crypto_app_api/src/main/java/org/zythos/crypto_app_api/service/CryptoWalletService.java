package org.zythos.crypto_app_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.zythos.crypto_app_api.dto.TransactionDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CryptoWalletService {

    private final WebClient webClient;


    public CryptoWalletService() {
        webClient = WebClient.builder().baseUrl("http://localhost:8083/").build();
    }

    public Mono<TransactionDTO[]> get(UUID idCustommer, String cryptoCurrencyName){
        return webClient.get().uri("wallet/"+idCustommer+"/"+cryptoCurrencyName).retrieve().bodyToMono(TransactionDTO[].class);
    }
}
