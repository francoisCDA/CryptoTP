package org.zythos.cryptoaddict_api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zythos.cryptoaddict_api.dao.CustomerDAO;
import org.zythos.cryptoaddict_api.dto.CustomerDTO;
import org.zythos.cryptoaddict_api.entity.Customer;
import org.zythos.cryptoaddict_api.exception.CustomerEmailExist;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerDAO customerDAO;


    public Mono<Customer> save(String email, String firstName, String lastName, String password, Double piggyBank) throws CustomerEmailExist {

        Mono<Customer> customerMono = customerDAO.findByEmail(email);

        Customer customer = (Customer) customerMono.block();

        if (customer == null) {
            Customer newCustomer = Customer.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
                    .piggyBank(piggyBank).build();

            customerDAO.save(newCustomer);

        }
        throw new CustomerEmailExist(email);
    }


}
