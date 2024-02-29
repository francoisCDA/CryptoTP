package org.example.cryptowallet_api.controller;

import org.example.cryptowallet_api.dto.TransactionDTO;
import org.example.cryptowallet_api.service.WalletService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.UUID;

@RestController
@RequestMapping("api/wallet")
public class WalletRestController {

    private final WalletService walletService;

    public WalletRestController(WalletService walletService) {
        this.walletService = walletService;
    }


    @GetMapping("{idPerson}/{cryptoCurrencyName}") // http://localhost:8098/api/wallet/{idPerson}/{cryptoCurrencyName}
    public Flux<TransactionDTO> getAll(@PathVariable("idPerson") UUID idPerson, @PathVariable("cryptoCurrencyName") String cryptoCurrencyName) {
        return walletService.getCryptoWalletByPersonId(idPerson, cryptoCurrencyName);
    }

    @PostMapping("post") // http://localhost:8083/api/wallet
    public void postTransaction(@RequestBody TransactionDTO transactionDTO){
        walletService.createNewTransaction(String.valueOf(transactionDTO.getTransactionDate()),
                        transactionDTO.getCryptoCurrencyName(),
                        transactionDTO.getQuantity(),
                        transactionDTO.getPrice(), UUID.randomUUID());
    }

}
