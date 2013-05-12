package com.dmytro.realty.engine.parser;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import com.dmytro.realty.engine.RealtyOffer;

public class AvisoRealtyParser extends AbstractJsoupRealtyParser {
	public static String OFFER = "line_ads white";
	public static String OFFER_LINK_CLASS = "link linkWithHash detailsLink {clickerID:'ads_title'}";

	public static String OFFER_PRICE_CLASS = "xxxx-large lheight24 margintop7 block not-arranged";
	public static String OFFER_CONTENT_CLASS = "margintop10 lheight20 large marginright40";
	public static String OFFER_CONTACT_CLASS = "block brkword lheight16";

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
