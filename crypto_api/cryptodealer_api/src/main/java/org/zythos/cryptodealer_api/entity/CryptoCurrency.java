package org.zythos.cryptodealer_api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


import java.time.LocalDateTime;

@Data
@Table(value = "crypto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CryptoCurrency {

    @Id
    private Long id;

    @Column(value = "crypto_name")
    private String name;

    @Column(value = "available_stock")
    private Double availableStock;

    @Column(value = "current_value")
    private Double currentValue;

    @Column(value = "log_time")
    private Long logTime;

}
