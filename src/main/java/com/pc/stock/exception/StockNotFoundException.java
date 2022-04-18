package com.pc.stock.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StockNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StockNotFoundException(String exception) {
        super(exception);
    }
}