package org.zythos.crypto_app_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zythos.crypto_app_api.dto.CryptoProfileDTO;
import org.zythos.crypto_app_api.dto.TransactionDTO;
import org.zythos.crypto_app_api.service.CryptoAddictService;
import org.zythos.crypto_app_api.service.CryptoDealerService;
import org.zythos.crypto_app_api.service.CryptoWalletService;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/apiv1")
public class CryptoAppController {

    public final CryptoWalletService cryptoWalletService;
    public final CryptoAddictService cryptoAddictService;
    public final CryptoDealerService cryptoDealerService;

    public CryptoAppController(CryptoWalletService cryptoWalletService, CryptoAddictService cryptoAddictService, CryptoDealerService cryptoDealerService) {
        this.cryptoWalletService = cryptoWalletService;
        this.cryptoAddictService = cryptoAddictService;
        this.cryptoDealerService = cryptoDealerService;
    }

    @GetMapping("/{idCustommer}")
    public Mono<CryptoProfileDTO> get(@PathVariable("idCustommer")UUID idCustommer){
        Mono<TransactionDTO[]> transactionDTOMono = cryptoWalletService.get(idCustommer, "test");

        return Mono.zip()
    }
}
