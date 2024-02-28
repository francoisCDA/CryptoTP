package org.example.cryptowallet_api.controller;

import org.example.cryptowallet_api.service.WalletService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/wallet")
public class WalletRestController {

    private final WalletService walletService;

    public WalletRestController(WalletService walletService) {
        this.walletService = walletService;
    }


}
