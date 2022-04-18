package com.pc.stock.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.pc.stock.controller.StockController;
import com.pc.stock.dto.StockDto;
import com.pc.stock.spi.StockSPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@WebMvcTest(controllers = StockController.class)
class StockIntegrationTest {
    @MockBean
    private StockSPI stockService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("add stock test")
    @Order(1)
    void addStockTest() throws Exception {
        StockDto stockDto = new StockDto(1L, "HCL", new BigDecimal("20.10"), LocalDateTime.now());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/stocks/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(stockDto));
        MvcResult result = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    @DisplayName("get all Stocks")
    @Order(2)
    void getAllStocks() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/stocks/");
        MvcResult result = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @DisplayName("get Stock By Stock Id")
    @Order(3)
    void getStockByIdTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/stocks/",
                1);
        MvcResult result = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @DisplayName("update stock price value by id ")
    @Order(4)
    void updateStockPriceValueTest() throws Exception {
        StockDto stockDto = new StockDto(1L, "HCL", new BigDecimal("20.10"), LocalDateTime.now());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.patch("/api/stocks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(stockDto));
        MvcResult result = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
