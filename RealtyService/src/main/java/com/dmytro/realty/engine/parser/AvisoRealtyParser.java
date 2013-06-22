package com.dmytro.realty.engine.parser;

import com.dmytro.realty.service.implementation.RealtyService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class AvisoRealtyParser extends AbstractJsoupProxyRealtyParser {
    public static String OFFER = "line_ads white";
    public static String OFFER_LINK_CLASS = "link linkWithHash detailsLink {clickerID:'ads_title'}";
    public static String OFFER_PRICE_CLASS = "xxxx-large lheight24 margintop7 block not-arranged";
    public static String OFFER_CONTENT_CLASS = "margintop10 lheight20 large marginright40";
    public static String OFFER_CONTACT_CLASS = "block brkword lheight16";

    @Override
    public Document getSource(String request) throws RealtyUnparsebleException {
        Document document = null;
        System.setProperty("http.proxyHost", RealtyService.proxy.getAddress());
        System.setProperty("http.proxyPort", RealtyService.proxy.getPort() + "");
        for (int i = 0; i < 5; i++)
            try {
                document = super.getSource(request);
                break;
            } catch (RealtyUnparsebleException rue) {
                continue;
            }
        System.setProperty("http.proxyHost", "");
        System.setProperty("http.proxyPort", "");
        if (document == null)
            throw new RealtyUnparsebleException("Slando proxy error");
        else
            return document;
    }

    @Override
    public void parseRequest(Document document, List<String> links)
            throws RealtyUnparsebleException {

        Elements divs = document.getElementsByAttributeValue("class", OFFER);
        for (Element div : divs)
            for (Element a : div.getElementsByTag("a"))
                links.add(a.attr("href"));
    }

    @Override
    protected String parsePrice(Document document) {
        String content = contentString(document);
        int begin = content.indexOf("Цена:") + 5;
        int end = content.indexOf("Тел:");
        return content.substring(begin, end);
    }

    @Override
    protected String parsePhone(Document document) {
        String content = contentString(document);
        int begin = content.indexOf("Тел:") + 4;
        return content.substring(begin);
    }

    @Override
    protected String parseDate(Document document) {
        String content = document.getElementsByAttributeValue("class", "span8").text();
        int begin = content.indexOf("Подано:") + 7;
        return content.substring(begin, content.indexOf("Источник"));
    }

    @Override
    protected String parseOffender(Document document) {
        return "Невідомо";
    }

    @Override
    protected String parseContent(Document document) {
        return document.getElementsByAttributeValue("style", "margin-top: 0;")
                .text();
    }

    private String contentString(Document doc) {
        return doc.getElementsByAttributeValue("class", "phone").text();
    }
}
