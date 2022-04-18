package com.pc.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {

    private Long id;

    private String name;

    private BigDecimal currentPrice;

    private LocalDateTime lastUpdate;
}
