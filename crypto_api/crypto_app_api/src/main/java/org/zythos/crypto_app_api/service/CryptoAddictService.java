package org.zythos.crypto_app_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CryptoAddictService {
    private final WebClient webClient;

    public CryptoAddictService() {
        webClient = WebClient.builder().baseUrl("http://localhost:8010/").build();
    }

}
