package com.dmytro.realty.engine.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dmytro
 * Date: 25.05.13
 * Time: 16:50
 * To change this template use File | Settings | File Templates.
 */
public class MirKvartirRealtyParser extends AbstractJsoupProxyRealtyParser {

    @Override
    protected void parseRequest(Document source, List<String> links) throws RealtyUnparsebleException {
        Elements divs = source.getElementsByAttributeValue("class", "f");
        divs.addAll(source.getElementsByAttributeValue("class", "o f"));

        for (Element div : divs)
            for (Element a : div.getElementsByTag("a"))
                if (a.attr("href").contains("/view/")
                        && !links.contains(requestBuilder.host() + a.attr("href")))
                    links.add(requestBuilder.host() + a.attr("href"));
    }

    @Override
    protected String parsePrice(Document document) {
        return document.getElementsByAttributeValue("class", "price_h2").text().replaceAll("\\D+", "");
    }

    @Override
    protected String parsePhone(Document document) {
        Elements contacts = document.getElementsByAttributeValue("class", "contacts");
        return contacts.first().getElementsByTag("li").first().text();
    }

    @Override
    protected String parseOffender(Document document) {
        Elements contacts = document.getElementsByAttributeValue("class", "contacts");
        return contacts.first().getElementsByTag("li").last().text();
    }

    @Override
    protected String parseDate(Document document) {
        String extra = document.getElementsByAttributeValue("class", "extra").text();
        if (extra.contains(":"))
            return extra.substring(extra.indexOf(":")+2, extra.indexOf(":") + 19);
        return extra;
    }


    @Override
    protected String parseContent(Document document) {
        return document.getElementsByAttributeValue("class", "text").html()
                .replace("<p>", "").replace("</p>", "");
    }
}
