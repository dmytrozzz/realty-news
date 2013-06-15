package com.dmytro.realty.engine.parser;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RealtorRealtyParser extends AbstractJsoupProxyRealtyParser {

	@Override
	protected void parseRequest(Document source, List<String> links)
			throws RealtyUnparsebleException {
		for (Element e : source.getElementsByAttributeValue("class", "txt ext"))
			for (Element a : e.getElementsByTag("a"))
				if (a.attr("href").contains("view") && !a.parent().hasClass("description"))
					links.add(requestBuilder.getProperties().getProperty(
							"PURE_HOST")
							+ a.attr("href"));
		System.out.println(links);
	}

	@Override
	protected String parsePrice(Document document) {
		return document.getElementsByAttributeValue("class", "view-price")
				.get(0).getElementsByAttributeValue("class", "right").text();
	}

	@Override
	protected String parsePhone(Document document) {
		return requestBuilder.getProperties().getProperty("PURE_HOST")
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
		Elements es =  document.getElementsByTag("dl").get(0).getElementsByTag("dd");
		return es.get(0).text() + es.get(3).text();
	}

}
