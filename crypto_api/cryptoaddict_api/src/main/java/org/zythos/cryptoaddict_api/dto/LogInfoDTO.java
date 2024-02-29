package org.zythos.cryptoaddict_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogInfoDTO {

    private String email;
    private String password;

}
