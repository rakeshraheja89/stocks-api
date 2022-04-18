package com.pc.stock.controller;

import com.pc.stock.dto.StockDto;
import com.pc.stock.spi.StockSPI;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;

/**
 * This class provide API endpoints for Stocks.
 */
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/stocks/")
public class StockController {

    StockSPI stockSpi;

    /**
     * Fetch all stocks details
     *
     * @param pageId   page id
     * @param pageSize page size
     * @author rakesh
     */
    @GetMapping
    public ResponseEntity<List<StockDto>> getStocks(
            @RequestParam(required = false, defaultValue = "0", value = "pageId") Integer pageId,
            @RequestParam(required = false, defaultValue = "5", value = "pageSize") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageId, pageSize);
        return new ResponseEntity<>(
                stockSpi.getAllStocks(pageable), HttpStatus.OK);
    }

    /**
     * Get stock by id
     *
     * @param id of the Stock
     * @return Stock that is found by id
     */
    @GetMapping("{id}")
    public ResponseEntity<StockDto> getStockById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok().body(stockSpi.getStock(id));
    }

    /**
     * Create stock in db if not exists otherwise return error
     *
     * @param stock to be created
     * @return created Stock
     */
    @PostMapping
    public ResponseEntity<String> addStock(@Valid @RequestBody final StockDto stock) {
        return new ResponseEntity<>(stockSpi.addStock(stock), HttpStatus.CREATED);
    }

    /**
     * patch stock by id
     *
     * @param id of the Stock
     * @return Stock that is found by id
     */
    @PatchMapping("{id}")
    public ResponseEntity<String> updateStock(@PathVariable final Long id,
                                              @Valid @RequestBody StockDto stock) {
        return new ResponseEntity<>(stockSpi.updateStock(id, stock), HttpStatus.OK);
    }

    /**
     * Delete stock by id
     *
     * @param id of the Stock
     * @return Stock that is found by id
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStock(@PathVariable final Long id) {
        return ResponseEntity.ok().body(stockSpi.deleteStock(id));
    }
}
