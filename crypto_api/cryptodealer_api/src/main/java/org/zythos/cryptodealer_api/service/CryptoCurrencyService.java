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

    private final Random random = new Random();


    public Mono<CryptoCurrency> save(String cryptoName) throws CryptocurrencyNameExist {

       Mono<CryptoCurrency> monoCrypto = cryptoCurrencyDAO.findByName(cryptoName);

       CryptoCurrency crypto = (CryptoCurrency) monoCrypto.subscribe();

        if (crypto == null) {

            crypto.setName(cryptoName);
            crypto.setAvailableStock(random.nextDouble(15000,100000));
            crypto.setCurrentValue(random.nextDouble(1,1000));
            crypto.setLogTime(LocalDateTime.now());

            return cryptoCurrencyDAO.save(crypto);

        }
        throw new CryptocurrencyNameExist(cryptoName);
    }

    public Flux<CryptoCurrency> getAll() {
        Flux<CryptoCurrency> odlStack = cryptoCurrencyDAO.getAllCryptoLastValue();

        Flux<CryptoCurrency> newStack = odlStack.flatMap(crypto -> {
            crypto.setCurrentValue(random.nextDouble(1,1000));
            crypto.setLogTime(LocalDateTime.now());
            return cryptoCurrencyDAO.save(crypto);
        });

        return newStack;
    }


    public Flux<CryptoCurrency> getCryptoLastActivity(String cryptoName, Long minutes) {

        Flux<CryptoCurrency> refreshCurrency = getAll();

        if (minutes <= 0 ) {
            return cryptoCurrencyDAO.findByNameLastValue(cryptoName);
        }

        LocalDateTime afterThat = LocalDateTime.now().minusMinutes(minutes);

        return cryptoCurrencyDAO.findCryptoByNameForPeriode(cryptoName ,afterThat);

    }
}
