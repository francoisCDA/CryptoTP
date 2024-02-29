package org.example.cryptowallet_api.controller;

import org.example.cryptowallet_api.dto.TransactionDTO;
import org.example.cryptowallet_api.service.WalletService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.UUID;

@RestController
@RequestMapping("api/wallets")
public class WalletRestController {

    private final WalletService walletService;

    public WalletRestController(WalletService walletService) {
        this.walletService = walletService;
    }


    @GetMapping("{idCustomer}/{cryptoCurrencyName}") // http://localhost:808083/api/wallets/{idCustomer}/{cryptoCurrencyName}
    public Flux<TransactionDTO> getAll(@PathVariable("idCustomer")String idCustomer, @PathVariable("cryptoCurrencyName") String cryptoCurrencyName) {
        return walletService.getCryptoWalletByPersonId(idCustomer, cryptoCurrencyName);
    }

    @GetMapping("{idCustomer}")// http://localhost:8083/api/wallets
    public Flux<TransactionDTO> getAll(@PathVariable("idCustomer")String idCustomer){
        return walletService.getWalletsByIdCustomer(idCustomer);
    }


    @PostMapping("post") // http://localhost:8083/api/wallets
    public void postTransaction(@RequestBody TransactionDTO transactionDTO){
        walletService.createNewTransaction(String.valueOf(transactionDTO.getTransactionDate()),
                        transactionDTO.getCryptoCurrencyName(),
                        transactionDTO.getQuantity(),
                        transactionDTO.getPrice(), transactionDTO.getIdCustomer());
    }

}
