package com.dmytro.realty.engine.parser;

import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dmytro.realty.engine.RealtyUnit;
import com.dmytro.realty.engine.builder.SlandoCriteriaConverter;

/**
 * Created with IntelliJ IDEA. User: dmytro Date: 28.01.13 Time: 21:44 To change
 * this template use File | Settings | File Templates.
 */
public class SlandoRealtyParser extends AbstractJsoupRealtyParser {

    public static String OFFERS_TABLE = "offers_table";
    public static String OFFER_LINK_CLASS = "link linkWithHash detailsLink clicker {clickerID:'ads_title'}";

    public static String OFFER_PRICE_CLASS = "xxxx-large lheight24 margintop7 block not-arranged";
    public static String OFFER_CONTENT_CLASS = "marginbott20 lheight20 large marginright40";
    public static String OFFER_CONTACT_CLASS = "block brkword lheight16";

    public Set<String> parseRequest(String request) throws RealtyUnparsebleException {
	Set<String> hrefs = new HashSet<>();
	Document document = getSource(request);

	Element element = document.getElementById(OFFERS_TABLE);
	Elements as = element.getElementsByAttributeValue("class", OFFER_LINK_CLASS);
	for (Element a : as) {
	    hrefs.add(a.attr("href"));
	}
	return hrefs;
    }

    public RealtyUnit parseOffer(String link) throws RealtyUnparsebleException {
	Document document = getSource(link);

	String price = document.getElementsByAttributeValue("class", OFFER_PRICE_CLASS).text();
	System.out.println("Price: " + price);
	String content = document.getElementsByAttributeValue("class", OFFER_CONTENT_CLASS).text();
	System.out.println("content: " + price);
	String phoneRef = getImage(document.getElementsByAttributeValue("rel", "phone"));
	System.out.println("phone: " + price);
	String contact = document.getElementsByAttributeValue("class", OFFER_CONTACT_CLASS).text();
	System.out.println("contact: " + price);

	return new RealtyUnit(link, price, contact, phoneRef, content);
    }

    private String getImage(Elements href) {
	String classAttr = href.attr("class");
	System.out.println("CLASS!!! " + classAttr);
	if (classAttr.length() > 5) {
	    String jsonObj = classAttr.substring(classAttr.indexOf("{"), classAttr.indexOf("}") + 1)
		    .replace("clickerID", "\"clickerID\"").replace("\'", "\"");
	    JSONObject obj = (JSONObject) JSONValue.parse(jsonObj);
	    String imageRef = SlandoCriteriaConverter.createImageAddress(obj.get("id") + "");
	    return imageRef;
	}
	return "No telephone";
    }
}
