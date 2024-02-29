package org.zythos.crypto_app_api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WalletDTO {


    public List<TransactionDTO> wallet;

}
