package org.zythos.cryptodealer_api.dao;

import io.r2dbc.spi.ConnectionFactory;
import lombok.AllArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.zythos.cryptodealer_api.entity.CryptoCurrency;
import org.zythos.cryptodealer_api.repository.CryptoCurrencyRepository;
import reactor.core.CorePublisher;
import reactor.core.publisher.Mono;

@Component
public class CryptoCurrencyDAO {

    private final ConnectionFactory connectionFactory;
    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    public CryptoCurrencyDAO(ConnectionFactory connectionFactory, CryptoCurrencyRepository cryptoCurrencyRepository) {
        this.connectionFactory = connectionFactory;
        DatabaseClient databaseClient = DatabaseClient.create(connectionFactory);

        Mono result = databaseClient
                .sql("CREATE TABLE IF NOT EXISTS crypto(id bigint primary key auto_increment )")...

        this.cryptoCurrencyRepository = cryptoCurrencyRepository;

    }


    public Mono<CryptoCurrency> findByName(String cryptoName) {
    }

    public CorePublisher<Object> save(CryptoCurrency crypto) {
    }

    public Mono<CryptoCurrency> findByNameIs(String crypto) {
    }
}
