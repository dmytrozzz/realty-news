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
public class MirKvartirRealtyParser extends AbstractJsoupRealtyParser {

    @Override
    protected void parseRequest(Document source, List<String> links) throws RealtyUnparsebleException {
        Elements divs = source.getElementsByAttributeValue("class", "f");
        for (Element div : divs)
            for (Element a : div.getElementsByTag("a"))
                links.add(requestBuilder.getProperties().getProperty(
                        "PURE_HOST") + a.attr("href"));
    }

    @Override
    protected String parsePrice(Document document) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected String parsePhone(Document document) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected String parseDate(Document document) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected String parseOffender(Document document) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected String parseContent(Document document) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
