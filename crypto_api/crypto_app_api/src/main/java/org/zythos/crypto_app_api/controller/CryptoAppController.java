package org.zythos.crypto_app_api.controller;

import org.springframework.web.bind.annotation.*;
import org.zythos.crypto_app_api.dto.*;
import org.zythos.crypto_app_api.service.CryptoAddictService;
import org.zythos.crypto_app_api.service.CryptoDealerService;
import org.zythos.crypto_app_api.service.CryptoWalletService;
import reactor.core.publisher.Mono;

import java.util.Arrays;
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
    public boolean post(@RequestBody CustomerDTO customerDTO){
        cryptoAddictService.post(customerDTO);
        return true;
    }

    @PostMapping("/login") // http://localhost:8080/api/v1/login
    public CustomerTransactionsDTO login(@RequestBody LogInfoDTO logInfoDTO){

        Mono<CustomerDTO> customerDTOMono = cryptoAddictService.login(logInfoDTO);
        String customerToken = customerDTOMono.block().getCustomerToken();
        Mono<TransactionDTO[]> customerTransactionsDTOMono = cryptoWalletService.getCryptoWallets(customerToken);

        return Mono.zip(customerDTOMono, customerTransactionsDTOMono).map(t -> CustomerTransactionsDTO.builder()
                .email(t.getT1().getEmail())
                .firstName(t.getT1().getFirstName())
                .lastName(t.getT1().getLastName())
                .piggyBank(t.getT1().getPiggyBank())
                .customerToken(t.getT1().getCustomerToken())
                .transactionsList(Arrays.asList(t.getT2()))
                .build()).block();
    }

    @GetMapping("/{customerToken}") // // http://localhost:8080/api/v1/*
    public CustomerTransactionsDTO get(@PathVariable("customerToken")String customerToken){

        //Mono<CustomerDTO> customerDTOMono = cryptoAddictService
        cryptoWalletService.getCryptoWallets(customerToken).block();
        return null;
    }

    @GetMapping("/{cryptoCurrencyName}") // http://localhost:8080/api/v1/*
    public CryptoCurrencyDTO getCrypto(){
        return null;
    }

    @GetMapping("/cryptos") // http://localhost:8080/api/v1/cryptos
    public List<CryptoCurrencyDTO> getAllCryptos(){
        return null;
    }






}
