package org.zythos.crypto_app_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.zythos.crypto_app_api.dto.CustomerDTO;
import org.zythos.crypto_app_api.dto.LogInfoDTO;
import reactor.core.publisher.Mono;

@Service
public class CryptoAddictService {
    private final WebClient webClient;

    public CryptoAddictService() {
        webClient = WebClient.builder().baseUrl("http://localhost:8010/").build();
    }

    public Mono<CustomerDTO> post(CustomerDTO customerDTO){
        return webClient.post().uri("api/customer").retrieve().bodyToMono(CustomerDTO.class);
    }


    //TODO : récupération des transactions -> composition avec le customer correspondant
    public Mono<CustomerDTO> login(LogInfoDTO logInfoDTO){
        return webClient.get().uri("api/customer/customer").retrieve().bodyToMono(CustomerDTO.class);
    }
}
