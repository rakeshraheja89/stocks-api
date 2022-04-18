package com.pc.stock.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Stock entity
 */
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @PrimaryKey
    @CassandraType(type = CassandraType.Name.BIGINT)
    private Long id;

    @Column("NAME")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String name;

    @Column("CURRENT_PRICE")
    @CassandraType(type = CassandraType.Name.DECIMAL)
    private BigDecimal currentPrice;

    @Column("LAST_UPDATE")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    private LocalDateTime lastUpdate;

}