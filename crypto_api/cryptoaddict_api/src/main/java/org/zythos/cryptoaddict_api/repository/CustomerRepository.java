package org.zythos.cryptoaddict_api.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import org.zythos.cryptoaddict_api.entity.Customer;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends R2dbcRepository<Customer, Long> {

    Mono<Customer> findByIdIs(Long id);
    Mono<Customer> findByEmailIs(String email);

}
