package org.zythos.cryptoaddict_api.dao;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.zythos.cryptoaddict_api.dto.CustomerDTO;
import org.zythos.cryptoaddict_api.entity.Customer;
import org.zythos.cryptoaddict_api.repository.CustomerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class CustomerDAO {

    private final ConnectionFactory connectionFactory;
    private final CustomerRepository customerRepository;

    private DatabaseClient databaseClient;


    public CustomerDAO(ConnectionFactory connectionFactory, CustomerRepository customerRepository) {
        this.connectionFactory = connectionFactory;
        this.customerRepository = customerRepository;
        databaseClient = DatabaseClient.create(connectionFactory);

        Mono result = (Mono) databaseClient
                .sql("CREATE TABLE IF NOT EXISTS customer(id int primary key auto_increment, firstname VARCHAR(50), lastname VARCHAR(50), email VARCHAR(155), password VARCHAR(50), piggy_bank DOUBLE PRECISION)")
                .then().doOnSuccess((Void) ->  {
                    System.out.println("Création de la table Ok");
                }).doOnError((Void) ->  {
                    System.out.println("Création de la table Not OK");
                });
        result.subscribe();


    }

    public Mono<Customer> save(Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    public Mono<Customer> findByEmail(String customerEmail) {
        return customerRepository.findByEmailIs(customerEmail);}

    public Flux<Customer> findAllCustomers (){
        return customerRepository.findAll();
    }

    public Mono<Customer> findCustomerByEmailAndPassword(String email, String password) {

        return databaseClient.sql("SELECT id, firstname, lastname, email, password, piggy_bank " +
                        "From customer " +
                        "WHERE email = :email " +
                        "AND password = :password;")
                .bind("email", email)
                .bind("password", password)
                .fetch()
                .one()
                .map(c -> Customer.builder()
                        .id(Integer.parseInt((c.get("id").toString())))
                        .firstName(c.get("firstname").toString())
                        .lastName(c.get("lastname").toString())
                        .email(c.get("email").toString())
                        .password(c.get("password").toString())
                        .piggyBank((Double) c.get("piggy_bank"))
                        .build());
    }

    }

