package com.dmytro.realty.engine.parser;

public class RealtyUnparsebleException extends Exception {
    public RealtyUnparsebleException(String message) {
	    super(message);
    }

    public RealtyUnparsebleException(String message, Throwable cause) {
        super(message,cause);
    }
}
