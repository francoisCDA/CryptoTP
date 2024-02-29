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

    @GetMapping // GET localhost:8099/api/cryptos
    public Flux<CryptoCurrency> getAll(){
        return cryptoCurrencyService.getAll();
    }

    @GetMapping("{cryptoName}") // GET localhost:8099/api/cryptos/{crypto}?minutes=0?hours=0?days=0
    public Flux<CryptoCurrency> get(@PathVariable String cryptoName,
                                    @RequestAttribute(value = "minutes", required = false) int minutes,
                                    @RequestAttribute(value = "hours", required = false) int hours,
                                    @RequestAttribute(value = "days", required = false) int days) {

        Long totalMinutes = (long) (minutes + hours * 60 + days * 24 * 60);

       return cryptoCurrencyService.getCryptoLastActivity(cryptoName,totalMinutes);


    }

    @GetMapping("{cryptoName}/buy") // GET localhost:8099/api/cryptos/{crypto}/buy?euro=100
    public Double buyCrypto(@PathVariable String cryptoName, @RequestParam("euro") Double euro){
        return cryptoCurrencyService.buy(cryptoName,euro);
    }


    @GetMapping("{cryptoName}/sold") // GET localhost:8099/api/cryptos/{crypto}/sold?quantity=100
    public Double sendCrypto(@PathVariable String cryptoName, @RequestParam("quantity") Double quantity) {
        return cryptoCurrencyService.sold(quantity,quantity);
    }


    @PostMapping // POST localhost:8099/api/cryptos
    public ResponseEntity<CryptoCurrency> addCrypto(@RequestBody String cryptoName) {
        try {
            CryptoCurrency crypto = cryptoCurrencyService.save(cryptoName).blockOptional().get();
            return new ResponseEntity<>(crypto, HttpStatus.OK);
        } catch (CryptocurrencyNameExist e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }






}
