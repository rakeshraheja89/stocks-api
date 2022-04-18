package com.pc.stock.spi;

import com.pc.stock.dto.StockDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockSPI {

    List<StockDto> getAllStocks(Pageable pageable);

    StockDto getStock(Long id);

    String addStock(StockDto stockDto);

    String updateStock(Long id, StockDto stock);

    String deleteStock(Long id);
}
