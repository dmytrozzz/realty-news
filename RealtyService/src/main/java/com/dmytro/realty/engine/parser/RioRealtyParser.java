package com.dmytro.realty.engine.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class RioRealtyParser extends AbstractJsoupProxyRealtyParser {

    @Override
    protected void parseRequest(Document source, List<String> links)
            throws RealtyUnparsebleException {
        Elements es = source
                .getElementsByAttributeValue("class", "head-ticket");
        for (Element e : es)
            for (Element a : e.getElementsByTag("a"))
                links.add(requestBuilder.host() + a.attr("href"));
    }

    @Override
    protected String parsePrice(Document document) {
        return document.getElementsByAttributeValue("class", "price bold")
                .text();
    }

    @Override
    protected String parsePhone(Document document) {
        return document.getElementsByAttributeValue("class", "phone").text();
    }

    @Override
    protected String parseDate(Document document) {
        return document.getElementsByAttributeValue("class", "icon-added-item")
                .get(0).getElementsByTag("strong").text();
    }

    @Override
    protected String parseOffender(Document document) {
        return document.getElementsByAttributeValue("class", "user-name")
                .get(0).children().attr("title");
    }

    @Override
    protected String parseContent(Document document) {
        return document.getElementsByAttributeValue("class",
                "box-panel rocon description-view").text();
    }
}
