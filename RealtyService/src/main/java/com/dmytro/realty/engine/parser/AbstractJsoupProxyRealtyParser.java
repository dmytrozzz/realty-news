package com.dmytro.realty.engine.parser;

import com.dmytro.realty.domain.RealtyOffer;
import com.dmytro.realty.engine.builder.ARealtyRequestBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractJsoupProxyRealtyParser implements IRealtyParser {

    protected ARealtyRequestBuilder requestBuilder;

    public void setRequestBuilder(ARealtyRequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    @Override
    public List<String> parseRequest(String request)
            throws RealtyUnparsebleException {
        List<String> links = new LinkedList<>();
        parseRequest(getSource(request), links);
        System.out.println(links);
        return links;
    }

    protected abstract void parseRequest(Document source, List<String> links)
            throws RealtyUnparsebleException;

    @Override
    public RealtyOffer parseOffer(String offerRequest)
            throws RealtyUnparsebleException {
        RealtyOffer offer = new RealtyOffer();
        offer.setLink(offerRequest);

        Document document = getSource(offerRequest);
        try {
            offer.setOffender(parseOffender(document));
            offer.setDate(parseDate(document));
            offer.setPhone(parsePhone(document));
            offer.setPrice(parsePrice(document));
            offer.setOfferContent(parseContent(document));
        } catch (Exception ex) {
            throw new RealtyUnparsebleException("Some parsing problem in " + offer.getLink(), ex);
        }

        return offer;
    }

    protected abstract String parsePrice(Document document);

    protected abstract String parsePhone(Document document);

    protected abstract String parseDate(Document document);

    protected abstract String parseOffender(Document document);

    protected abstract String parseContent(Document document);

    @Override
    public Document getSource(String request) throws RealtyUnparsebleException {
        try {
            URI uri = new URI(request);
            return Jsoup.parse(uri.toURL(), 20000);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            throw new RealtyUnparsebleException("Can't parse the link: "
                    + request, e);
        }
    }
}
