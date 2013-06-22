package com.dmytro.realty.engine.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class RealtorRealtyParser extends AbstractJsoupProxyRealtyParser {

    @Override
    protected void parseRequest(Document source, List<String> links)
            throws RealtyUnparsebleException {
        for (Element e : source.getElementsByAttributeValue("class", "txt ext"))
            for (Element a : e.getElementsByTag("a"))
                if (a.attr("href").contains("view") && !a.parent().hasClass("description"))
                    links.add(requestBuilder.host() + a.attr("href"));
    }

    @Override
    protected String parsePrice(Document document) {
        Elements price = document.getElementsByAttributeValue("class", "view-price");
        if (price.size() > 0)
            return price.get(0).getElementsByAttributeValue("class", "right").text();
        return "?";
    }

    @Override
    protected String parsePhone(Document document) {
        return requestBuilder.host()
                + document
                .getElementsByAttributeValue("class",
                        "contact-info-phone").get(0)
                .getElementsByTag("img").attr("src");
    }

    @Override
    protected String parseDate(Document document) {
        return document.getElementsByAttributeValue("class", "bordered").get(0)
                .getElementsByTag("dt").get(0).text();
    }

    @Override
    protected String parseOffender(Document document) {
        return document.getElementsByClass("name").text();
    }

    @Override
    protected String parseContent(Document document) {
        Element firstElement = document.getElementsByAttributeValue("class", "txt-box").first();
        if (!firstElement.getElementsContainingOwnText("Описание").isEmpty())
            return firstElement.getElementsContainingOwnText("Описание").get(0).nextElementSibling().text();
        else if (!firstElement.getElementsContainingOwnText("Детали").isEmpty())
            return firstElement.getElementsContainingOwnText("Детали").get(0).nextElementSibling().text();
        else if (!firstElement.getElementsContainingOwnText("В квартире есть").isEmpty())
            return firstElement.getElementsContainingOwnText("В квартире есть").get(0).nextElementSibling().text();
        return "";
    }

}
