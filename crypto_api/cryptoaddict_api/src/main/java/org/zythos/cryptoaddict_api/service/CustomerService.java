package org.zythos.cryptoaddict_api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zythos.cryptoaddict_api.dao.CustomerDAO;
import org.zythos.cryptoaddict_api.dto.CustomerDTO;
import org.zythos.cryptoaddict_api.dto.LogInfoDTO;
import org.zythos.cryptoaddict_api.entity.Customer;
import org.zythos.cryptoaddict_api.exception.CustomerEmailExist;
import org.zythos.cryptoaddict_api.exception.CustomerEmailOrPasswordIsWrong;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerDAO customerDAO;


    public Mono<Customer> save(String email, String firstName, String lastName, String password, Double piggyBank) throws CustomerEmailExist {

        Mono<Customer> customerMono = customerDAO.findByEmail(email);

        Customer customer = (Customer) customerMono.block();

        if (customer == null) {
            Customer newCustomer = Customer.builder()
                    .customerToken(UUID.randomUUID().toString())
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
                    .piggyBank(piggyBank).build();

          return  customerDAO.save(newCustomer);

        }
        throw new CustomerEmailExist(email);
    }

    public Flux<Customer> getAllCustomer() {
        Flux<Customer> customers = customerDAO.findAllCustomers();
        return customers;
    }

    public Mono<Customer> getCustomerByEmailAndPassword(String email, String password) throws CustomerEmailOrPasswordIsWrong {

        Mono<Customer> customerMono = customerDAO.findCustomerByEmailAndPassword(email,password);

        Customer customer = (Customer) customerMono.block();

        try {
            if (customer != null) {
                return customerMono;
            }
        }catch (Exception e){
            throw new CustomerEmailOrPasswordIsWrong(email,password);
        }
        return customerMono;

    }

    public Mono<String> getToken(String email, String password) throws CustomerEmailOrPasswordIsWrong{
        return customerDAO.getTokenWhenAuth(LogInfoDTO.builder()
                .email(email)
                .password(password)
                .build());
    }


}
