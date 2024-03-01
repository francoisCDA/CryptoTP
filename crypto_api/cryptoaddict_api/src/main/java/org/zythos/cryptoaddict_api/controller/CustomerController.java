package org.zythos.cryptoaddict_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zythos.cryptoaddict_api.dto.CustomerDTO;
import org.zythos.cryptoaddict_api.dto.LogInfoDTO;
import org.zythos.cryptoaddict_api.entity.Customer;
import org.zythos.cryptoaddict_api.exception.CustomerEmailExist;
import org.zythos.cryptoaddict_api.exception.CustomerEmailOrPasswordIsWrong;
import org.zythos.cryptoaddict_api.service.CustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping// POST localhost:8010/api/customer
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        try {
            Customer newCustomer = customerService.save(customer.getEmail(), customer.getFirstName(), customer.getLastName(), customer.getPassword(), customer.getPiggyBank()).blockOptional().get();
            return new ResponseEntity<>(newCustomer, HttpStatus.OK);
        } catch (CustomerEmailExist e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/{id}")
//    public Flux<Customer> getCustomerById

    @GetMapping("/customers")// GET localhost:8010/api/customer/customers
    public Flux<Customer> getAll(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/customer")
    public Mono<Customer> getCustomerByEmailAndPassword(@RequestBody LogInfoDTO logInfoDTO) throws CustomerEmailOrPasswordIsWrong {
        return customerService.getCustomerByEmailAndPassword(logInfoDTO.getEmail(),logInfoDTO.getPassword());
    }

    @GetMapping("/customerToken")
    public Mono<String> auth(@RequestBody LogInfoDTO logInfoDTO) throws CustomerEmailOrPasswordIsWrong {
        return customerService.getToken(logInfoDTO.getEmail(), logInfoDTO.getPassword());
    }
}
