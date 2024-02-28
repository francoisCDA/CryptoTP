package org.zythos.cryptodealer_api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zythos.cryptodealer_api.dao.CryptoCurrencyDAO;
import org.zythos.cryptodealer_api.entity.CryptoCurrency;
import org.zythos.cryptodealer_api.exception.CryptocurrencyNameExist;
import org.zythos.cryptodealer_api.repository.CryptoCurrencyRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class CryptoCurrencyService {

    private final CryptoCurrencyDAO cryptoCurrencyDAO;


    public Mono<CryptoCurrency> save(String cryptoName) throws CryptocurrencyNameExist {

       Mono<CryptoCurrency> monoCrypto = cryptoCurrencyDAO.findByName(cryptoName);

       CryptoCurrency crypto = (CryptoCurrency) monoCrypto.subscribe();

        if (crypto == null) {
            Random random = new Random();
            crypto.setName(cryptoName);
            crypto.setAvailableStock(random.nextDouble(15000,100000));
            crypto.setCurrentValue(random.nextDouble(1,1000));
            crypto.setLogTime(LocalDateTime.now());

            return (Mono<CryptoCurrency>) cryptoCurrencyDAO.save(crypto).subscribe();
        }
        throw new CryptocurrencyNameExist(cryptoName);
    }

    public Mono<CryptoCurrency> findByName(String crypto) {
        return cryptoCurrencyDAO.findByNameIs(crypto);
    }

    public Flux<CryptoCurrency> getAll() {

    }
}
