package org.zythos.cryptodealer_api.dao;

import io.r2dbc.spi.ConnectionFactory;
import lombok.AllArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.zythos.cryptodealer_api.entity.CryptoCurrency;
import org.zythos.cryptodealer_api.repository.CryptoCurrencyRepository;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class CryptoCurrencyDAO {

    private final ConnectionFactory connectionFactory;
    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    private DatabaseClient databaseClient;

    public CryptoCurrencyDAO(ConnectionFactory connectionFactory, CryptoCurrencyRepository cryptoCurrencyRepository) {
        this.connectionFactory = connectionFactory;
        databaseClient = DatabaseClient.create(connectionFactory);

        Mono result = (Mono) databaseClient.sql("CREATE TABLE IF NOT EXISTS crypto(id bigint AUTO_INCREMENT PRIMARY KEY, crypto_name VARCHAR(20), available_stock DOUBLE PRECISION, current_value DOUBLE PRECISION, log_time BIGINT);")
                .then().doOnSuccess((Void) ->  {
                    System.out.println("Création de la table OK");
                }).doOnError((Void) ->  {
                    System.out.println("Création de la table Not OK");
                });

        result.subscribe();

        this.cryptoCurrencyRepository = cryptoCurrencyRepository;

    }

    public Mono<CryptoCurrency> findByName(String cryptoName) {
        return cryptoCurrencyRepository.findByNameIs(cryptoName);
    }

    public Mono<CryptoCurrency> save(CryptoCurrency newCrypto) {
        return cryptoCurrencyRepository.save(newCrypto);
    }

    public Flux<CryptoCurrency> findByNameLastValue(String cryptoName) {

        return databaseClient.sql("SELECT id, crypto_name, available_stock, current_value, log_time " +
                "FROM crypto" +
                "WHERE crypto_name = ':cryptoName'" +
                "ORDER BY log_time DESC" +
                "LIMIT 1;")
                .bind("cryptoName", cryptoName)
                .fetch()
                .all()
                .map(cr -> CryptoCurrency.builder()
                       // .id(Long.valueOf(cr.get("id").toString()))
                        .name(cr.get("crypto_name").toString())
                        .availableStock(Double.valueOf(cr.get("available_stock").toString()))
                        .currentValue(Double.valueOf(cr.get("current_value").toString()))
                        .logTime(Long.valueOf(cr.get("log_time").toString()))
                        .build());
    }

    public Flux<CryptoCurrency> findCryptoByNameForPeriode(String cryptoName, Long afterThat) {

        return databaseClient.sql("SELECT id, crypto_name, available_stock, current_value, log_time " +
                        "FROM crypto" +
                        "WHERE crypto_name = :cryptoName" +
                        "AND log_time >= :afterThat" +
                        "ORDER BY log_time DESC" +
                        "LIMIT 1;")
                .bind("cryptoName", cryptoName)
                .bind("afterThat", afterThat)
                .fetch()
                .all()
                .map(cr -> CryptoCurrency.builder()
                      //  .id(Long.valueOf(cr.get("id").toString()))
                        .name(cr.get("crypto_name").toString())
                        .availableStock(Double.valueOf(cr.get("available_stock").toString()))
                        .currentValue(Double.valueOf(cr.get("current_value").toString()))
                        .logTime(Long.valueOf(cr.get("log_time").toString()))
                        .build());


    }

    public Flux<CryptoCurrency> getAllCryptoLastValue() {
        return databaseClient.sql("SELECT c.* " +
                        "FROM crypto c " +
                        "INNER JOIN ( " +
                        "    SELECT crypto_name, MAX(log_time) AS max_log_time " +
                        "    FROM crypto " +
                        "    GROUP BY crypto_name " +
                        ") max_log ON c.crypto_name = max_log.crypto_name AND c.log_time = max_log.max_log_time;")
                .fetch()
                .all()
                .map(cr -> CryptoCurrency.builder()
                       // .id(Long.valueOf(cr.get("id").toString()))
                        .name(cr.get("crypto_name").toString())
                        .availableStock(Double.valueOf(cr.get("available_stock").toString()))
                        .currentValue(Double.valueOf(cr.get("current_value").toString()))
                        .logTime(Long.valueOf(cr.get("log_time").toString()))
                        .build());
    }
}
