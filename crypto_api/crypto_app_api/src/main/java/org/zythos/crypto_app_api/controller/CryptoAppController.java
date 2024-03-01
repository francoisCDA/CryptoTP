package org.zythos.crypto_app_api.controller;

import org.springframework.web.bind.annotation.*;
import org.zythos.crypto_app_api.dto.*;
import org.zythos.crypto_app_api.service.CryptoAddictService;
import org.zythos.crypto_app_api.service.CryptoDealerService;
import org.zythos.crypto_app_api.service.CryptoWalletService;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CryptoAppController {

    public final CryptoWalletService cryptoWalletService;
    public final CryptoAddictService cryptoAddictService;
    public final CryptoDealerService cryptoDealerService;

    public CryptoAppController(CryptoWalletService cryptoWalletService, CryptoAddictService cryptoAddictService, CryptoDealerService cryptoDealerService) {
        this.cryptoWalletService = cryptoWalletService;
        this.cryptoAddictService = cryptoAddictService;
        this.cryptoDealerService = cryptoDealerService;
    }

    @PostMapping("/addcustomer")  // http://localhost:8080/api/v1/addcustomer
    public boolean post(@RequestBody CustomerDTO custommerDTO){
        return false;
    }

    @PostMapping("/login") // http://localhost:8080/api/v1/login
    public String login(@RequestBody LogInfoDTO logInfoDTO){
        return "";
    }

    @GetMapping("/cryptos") // http://localhost:8080/api/v1/cryptos
    public List<CryptoCurrencyDTO> getAllCryptos(){
        return null;
    }

    @GetMapping("/{cryptoCurrencyName}") // http://localhost:8080/api/v1/*
    public CryptoCurrencyDTO getCrypto(){
        return null;
    }



    @GetMapping("/{idCustomer}") // // http://localhost:8080/api/v1/*
    public CryptoProfileDTO get(@PathVariable("idCustomer")UUID idCustomer){
        return null;
    }


}
