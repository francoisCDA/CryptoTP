package org.zythos.crypto_app_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CryptoDealerService {

    private final WebClient webClient;


    public CryptoDealerService() {
        webClient = WebClient.builder().baseUrl("http://localhost:8099/").build();
    }
}
