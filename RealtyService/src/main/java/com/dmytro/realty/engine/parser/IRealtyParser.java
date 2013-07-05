package com.dmytro.realty.engine.parser;

import java.util.List;

import com.dmytro.realty.domain.RealtyOffer;
import com.dmytro.realty.engine.builder.ARealtyRequestBuilder;

public interface IRealtyParser {
	void setRequestBuilder(ARealtyRequestBuilder requestBuilder);
	
    /**
     * Parses request with search criteria parameters
     * 
     * @param request
     *            Builded http request with all search parameters
     * @return List of parsed offer links
     * @throws RealtyUnparsebleException
     */
    List<String> parseRequest(String request) throws RealtyUnparsebleException;

    /**
     * Parses one offer
     * 
     * @param offerRequest
     *            request link to offer page
     * @return {@link RealtyOffer} instance based on parsed information
     * @throws RealtyUnparsebleException
     */
    RealtyOffer parseOffer(String offerRequest) throws RealtyUnparsebleException;

    /**
     * Returns an object, that acts like parser source. Can be used by different
     * parsers, based on different technologies.
     * 
     * @param request
     *            Builded http request with all search parameters
     * @return DOM, SAX or another representation of parsed source
     * @throws RealtyUnparsebleException
     */
    Object getSource(String request) throws RealtyUnparsebleException;
}
