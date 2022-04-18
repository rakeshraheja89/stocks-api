package com.pc.stock;

import com.pc.stock.spi.StockSPI;
import com.pc.stock.util.StocksBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class StockApplication {

	@Autowired
	StockSPI stockSpi;

	public static void main(String[] args)
	{
		SpringApplication.run(StockApplication.class, args);
	}

	/**
	 * This will create initial list of stocks on application startup.
	 */
	@PostConstruct
	void prepareStocks() {
		var stocks = StocksBuilder.build();
		stocks.stream().forEach(x->stockSpi.addStock(x));
	}

}
