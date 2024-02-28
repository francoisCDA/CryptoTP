package org.zythos.cryptodealer_api.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import org.zythos.cryptodealer_api.entity.CryptoCurrency;
import reactor.core.publisher.Mono;

@Repository
public interface CryptoCurrencyRepository extends R2dbcRepository<CryptoCurrency, Long> {

    Mono<CryptoCurrency> findByNameIs(String name);

}
