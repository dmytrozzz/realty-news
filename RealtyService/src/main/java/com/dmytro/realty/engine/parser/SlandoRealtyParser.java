package com.dmytro.realty.engine.parser;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//import com.dmytro.realty.engine.builder.SlandoCriteriaConverter;

/**
 * Created with IntelliJ IDEA. User: dmytro Date: 28.01.13 Time: 21:44 To change
 * this template use File | Settings | File Templates.
 */
public class SlandoRealtyParser extends AbstractJsoupProxyRealtyParser {

	public static String OFFERS_TABLE = "offers_table";
	public static String OFFER_LINK_CLASS = "link linkWithHash detailsLink {clickerID:'ads_title'}";

	public static String OFFER_PRICE_CLASS = "xxxx-large lheight24 margintop7 block not-arranged";
	public static String OFFER_CONTENT_CLASS = "margintop10 lheight20 large marginright40";
	public static String OFFER_CONTACT_CLASS = "block brkword lheight16";

	public void parseRequest(Document document, List<String> links) throws RealtyUnparsebleException {

		Element element = document.getElementById(OFFERS_TABLE);
		Elements as = element.getElementsByAttributeValue("class", OFFER_LINK_CLASS);
		for (Element a : as) {
			links.add(a.attr("href"));
		}
	}

	private String getImage(Elements href) {
		String classAttr = href.attr("class");
		if (classAttr.length() > 5) {
			String jsonObj = classAttr.substring(classAttr.indexOf("{"), classAttr.indexOf("}") + 1)
					.replace("clickerID", "\"clickerID\"").replace("\'", "\"");
			JSONObject obj = (JSONObject) JSONValue.parse(jsonObj);
			
			String imageRef = requestBuilder.getProperties().getProperty("PURE_HOST")
					+ requestBuilder.getProperties().getProperty("PHONE") + obj.get("id") + "/?nomobile=1";
			
			return imageRef;
		}
		return "Невідомо";
	}

	@Override
	protected String parsePrice(Document document) {
		return document.getElementsByAttributeValue("class", OFFER_PRICE_CLASS).text();
	}

	@Override
	protected String parsePhone(Document document) {
		return getImage(document.getElementsByAttributeValue("rel", "phone"));
	}

	@Override
	protected String parseDate(Document document) {
		return document.getElementsByAttributeValue("class", "pding0_10 ").text();
	}

	@Override
	protected String parseOffender(Document document) {
		return document.getElementsByAttributeValue("class", OFFER_CONTACT_CLASS).text();
	}

	@Override
	protected String parseContent(Document document) {
		return document.getElementsByAttributeValue("class", OFFER_CONTENT_CLASS).text();
	}
}
