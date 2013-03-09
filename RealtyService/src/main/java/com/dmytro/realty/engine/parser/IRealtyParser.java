package com.dmytro.realty.engine.parser;

import java.util.Set;

import com.dmytro.realty.engine.RealtyUnit;

public interface IRealtyParser {
    Set<String> parseRequest(String request) throws RealtyUnparsebleException;

    RealtyUnit parseOffer(String offerRequest) throws RealtyUnparsebleException;

    Object getSource(String request) throws RealtyUnparsebleException;
}
