package org.zythos.cryptodealer_api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zythos.cryptodealer_api.dao.CryptoCurrencyDAO;
import org.zythos.cryptodealer_api.entity.CryptoCurrency;
import org.zythos.cryptodealer_api.exception.CryptocurrencyNameExist;
import org.zythos.cryptodealer_api.exception.OutOfStockException;
import org.zythos.cryptodealer_api.repository.CryptoCurrencyRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class CryptoCurrencyService {

    private final CryptoCurrencyDAO cryptoCurrencyDAO;

    private final Random random;

    public CryptoCurrencyService(CryptoCurrencyDAO cryptoCurrencyDAO) {
        this.cryptoCurrencyDAO = cryptoCurrencyDAO;
        random  = new Random();
    }


    public Mono<CryptoCurrency> save(String cryptoName) throws CryptocurrencyNameExist {

       Mono<CryptoCurrency> monoCrypto = cryptoCurrencyDAO.findByName(cryptoName);

       CryptoCurrency crypto = (CryptoCurrency) monoCrypto.block();

        if (crypto == null) {
            crypto = new CryptoCurrency();
            crypto.setName(cryptoName);
            crypto.setAvailableStock(random.nextDouble(15000,100000));
            crypto.setCurrentValue(random.nextDouble(1,1000));
            crypto.setLogTime(System.currentTimeMillis()/1000L);

            return cryptoCurrencyDAO.save(crypto);

        }
        throw new CryptocurrencyNameExist(cryptoName);
    }

    public Flux<CryptoCurrency> getAll() {
        Flux<CryptoCurrency> odlStack = cryptoCurrencyDAO.getAllCryptoLastValue();

        Flux<CryptoCurrency> newStack = odlStack.flatMap(crypto -> {
            crypto.setCurrentValue(crypto.getCurrentValue() * random.nextDouble(0.5,1.6));
            crypto.setLogTime(System.currentTimeMillis()/1000L);
            return cryptoCurrencyDAO.save(crypto);
        });

        return newStack;
    }


    public Flux<CryptoCurrency> getCryptoLastActivity(String cryptoName, Long minutes) {

        Flux<CryptoCurrency> refreshCurrency = getAll();

        if (minutes <= 0 ) {
            return cryptoCurrencyDAO.findByNameLastValue(cryptoName);
        }

        Long afterThat = System.currentTimeMillis()/1000L - minutes * 60;

        return cryptoCurrencyDAO.findCryptoByNameForPeriode(cryptoName ,afterThat);

    }

    public Double buy(String cryptoName, Double euro) {
        CryptoCurrency cryptoToBuy = cryptoCurrencyDAO.findByNameLastValue(cryptoName).blockFirst();

        Double cryptoQuantity = euro / cryptoToBuy.getCurrentValue() ;
        Double newQuantity = cryptoToBuy.getAvailableStock()-cryptoQuantity;

        if (newQuantity < 0 ) throw new OutOfStockException(cryptoName);

        cryptoToBuy.setAvailableStock(newQuantity);
        cryptoToBuy.setCurrentValue(cryptoToBuy.getCurrentValue() * random.nextDouble(0.9,2));

        cryptoCurrencyDAO.save(cryptoToBuy).subscribe();

        return cryptoQuantity;
    }

    public Double sold(String cryptoName, Double quantity) {

        CryptoCurrency cryptoToSold = cryptoCurrencyDAO.findByNameLastValue(cryptoName).blockFirst();

        Double euro = cryptoToSold.getCurrentValue() * quantity;

        cryptoToSold.setAvailableStock(cryptoToSold.getAvailableStock()+quantity);
        cryptoToSold.setCurrentValue(cryptoToSold.getCurrentValue() * random.nextDouble(0.4,1.1));

        cryptoCurrencyDAO.save(cryptoToSold).subscribe();

        return euro;
    }
}
