
package com.pc.stock.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pc.stock.dto.StockDto;
import com.pc.stock.exception.StockNotFoundException;
import com.pc.stock.persistence.domain.Stock;
import com.pc.stock.persistence.repository.StockRepository;
import com.pc.stock.spi.StockSPI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import static com.pc.stock.constant.StocksConstant.DELETE_MESSAGE;
import static com.pc.stock.constant.StocksConstant.UPDATE_MESSAGE;

@Service
@AllArgsConstructor
@Slf4j
public class StockServiceImpl implements StockSPI {

	private StockRepository stockRepository;

	private ObjectMapper objectMapper;

	@Override
	public List<StockDto> getAllStocks(final Pageable pageable) {
		log.info("get all stocks");
		final var stocks = stockRepository.findAll(pageable);
		if(stocks.hasContent())
		{
			return stocks.stream().map(x->objectMapper.convertValue(x, StockDto.class))
					.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	@Override
	public StockDto getStock(final Long id) {
		log.info("getting stock by stock id {}" , id );
		Stock stock = stockRepository.findById(id)
				.orElseThrow(() -> new StockNotFoundException("Stock does not exist"));
		return objectMapper.convertValue(stock, StockDto.class);
	}

	@Override
	public String addStock(final StockDto stockDto) {
		log.info("adding new stock {} ", stockDto.getName());
		Stock stock = objectMapper.convertValue(stockDto, Stock.class);
		return stockRepository.save(stock).getName() + " inserted successfully";
	}

	@Override
	public String updateStock(final Long id, final StockDto stockDto) {
		log.info("update stock for id {} ", id);
		var stockObj = getStock(id);
		stockObj.setCurrentPrice(stockDto.getCurrentPrice());
		final var stock = objectMapper.convertValue(stockObj, Stock.class);
		return StringUtils.join(UPDATE_MESSAGE ,stockRepository.save(stock).getName());
	}

	@Override
	public String deleteStock(final Long id) {
		log.info("delete stock for id {} ", id);
		final var stock = objectMapper.convertValue(getStock(id), Stock.class);
		stockRepository.delete(stock);
		return DELETE_MESSAGE;
	}
}
