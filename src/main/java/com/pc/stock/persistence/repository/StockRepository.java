package com.pc.stock.persistence.repository;

import com.pc.stock.persistence.domain.Stock;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface StockRepository extends CassandraRepository<Stock, Long> {
}
