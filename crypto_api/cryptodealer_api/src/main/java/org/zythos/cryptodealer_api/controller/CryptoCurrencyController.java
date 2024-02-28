package org.zythos.cryptodealer_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zythos.cryptodealer_api.entity.CryptoCurrency;
import org.zythos.cryptodealer_api.exception.CryptocurrencyNameExist;
import org.zythos.cryptodealer_api.service.CryptoCurrencyService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/cryptos")
@RequiredArgsConstructor
public class CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;

    @GetMapping
    public Flux<CryptoCurrency> getAll(){
        return cryptoCurrencyService.getAll();
    }

    @GetMapping("{crypto}")
    public Mono<CryptoCurrency> get(@PathVariable String crypto) {
        return cryptoCurrencyService.findByName(crypto);
    }

    @PostMapping
    public ResponseEntity<CryptoCurrency> addCrypto(@RequestBody String cryptoName) {
        try {
            CryptoCurrency crypto = cryptoCurrencyService.save(cryptoName).blockOptional().get();
            return new ResponseEntity<>(crypto, HttpStatus.OK);
        } catch (CryptocurrencyNameExist e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }




}
