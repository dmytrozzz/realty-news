package com.dmytro.realty.logic.engine.parser;

import java.util.List;

import com.dmytro.realty.logic.engine.RealtyUnit;

public interface IRealtyParser {
    List<String> parseRequest(String request) throws RealtyUnparsebleException;

    RealtyUnit parseOffer(String offerRequest) throws RealtyUnparsebleException;

    Object getSource(String request) throws RealtyUnparsebleException;
}
