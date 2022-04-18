package com.pc.stock.util;

import com.pc.stock.dto.StockDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StocksBuilder {

    public static List<StockDto> build() {
        List<StockDto> stocks = new ArrayList<>();
        stocks.add(new StockDto(1L, "Apple", new BigDecimal("10.10"),
                LocalDateTime.now()));
        stocks.add(new StockDto(2L, "Amazon", new BigDecimal("20.10"),
                LocalDateTime.now()));
        stocks.add(new StockDto(3L, "FlipKart", new BigDecimal("30.10"),
                LocalDateTime.now()));
        stocks.add(new StockDto(4L, "Tesla", new BigDecimal("40.10"),
                LocalDateTime.now()));
        stocks.add(new StockDto(5L, "PayConiq", new BigDecimal("50.10"),
                LocalDateTime.now()));
        return stocks;
    }
}
