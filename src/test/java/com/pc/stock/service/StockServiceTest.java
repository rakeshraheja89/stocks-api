package com.pc.stock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pc.stock.dto.StockDto;
import com.pc.stock.persistence.domain.Stock;
import com.pc.stock.persistence.repository.StockRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {
    @Mock
    private StockRepository stockServiceRepository;
    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private StockServiceImpl stockServiceImpl;

    @Test
    @DisplayName("adding new stock")
    @Order(1)
    void testSaveStock() {
        final var stockDto = new StockDto(1L, "Apple", new BigDecimal("10.10"),
                LocalDateTime.now());
        Stock stock = Stock.builder().id(stockDto.getId())
                .name(stockDto.getName())
                .currentPrice(stockDto.getCurrentPrice())
                .lastUpdate(stockDto.getLastUpdate())
                .build();
        when(objectMapper.convertValue(stockDto, Stock.class)).thenReturn(stock);
        when(stockServiceRepository.save(stock)).thenReturn(stock);
        String responseMessage = stockServiceImpl.addStock(stockDto);
        if (!responseMessage.contains("Apple")) {
            fail();
        }
    }

    @Test
    @DisplayName("get all stocks")
    @Order(2)
    void testGetAllStocks() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock(1L, "Apple", new BigDecimal("10.10"),
                LocalDateTime.now()));
        stocks.add(new Stock(2L, "Amazon", new BigDecimal("20.10"),
                LocalDateTime.now()));
        Slice<Stock> pageStocks = new SliceImpl<Stock>(stocks);
        Pageable pageable = PageRequest.of(0, 1);
        when(stockServiceRepository.findAll(pageable)).thenReturn(pageStocks);
        List<StockDto> stockList = stockServiceImpl.getAllStocks(pageable);
        assertEquals(stocks.size(), stockList.size());
    }

    @Test
    @DisplayName("Get Stock By Stock Id")
    @Order(3)
    void testGetStockByStockName() {
        StockDto stockDto = new StockDto(1L, "Apple", new BigDecimal("10.10"),
                LocalDateTime.now());
        Stock stock = new Stock(1L, "Apple", new BigDecimal("10.10"),
                LocalDateTime.now());
        when(stockServiceRepository.findById(stockDto.getId())).thenReturn(Optional.of(stock));
        when(objectMapper.convertValue(stock, StockDto.class)).thenReturn(stockDto);
        StockDto stockDto2 = stockServiceImpl.getStock(stockDto.getId());
        assertEquals(stockDto2.getName(), stockDto.getName());
    }
}
