package com.dmytro.realty.logic.engine;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dmytro.realty.logic.engine.builder.SlandoCriteriaConverter;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: dmytro
 * Date: 28.01.13
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 */
public class RealtyParser {
    public Set<String> linkSet = new HashSet<>();    

    public List<String> parseOffersList(String address) {
        List<String> newHrefs = new ArrayList<>();        
        try {
            Document document = Jsoup.parse(new URL(address), 10000);
            Element element = document.getElementById("offers_table");
            Elements as = element.getElementsByAttributeValue("class", "link linkWithHash clicker {clickerID:'ads_title'}");
            for (Element a : as) {
                String href = a.attr("href");
                if (!linkSet.contains(href)) {
                    linkSet.add(href);
                    newHrefs.add(href);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newHrefs;
    }

    public RealtyUnit parseOffer(String link) {        
        String result = "";
        Document document = null;
        try {
            URI uri = new URI(link);
            document = Jsoup.parse(uri.toURL(), 20000);
        } catch (URISyntaxException | IOException e) {
            result += link + "\n" + "Can't parse!!!";
        }
        String price = document.getElementsByAttributeValue("class", "xxxx-large lheight24 margintop7 block not-arranged").text();
        String content = document.getElementsByAttributeValue("class", "marginbott20 lheight20 large marginright40").text();
        String phoneRef = getImage(document.getElementsByAttributeValue("rel", "phone"));
        String contact = document.getElementsByAttributeValue("class", "block brkword lheight16").text();
        System.out.println(phoneRef);
        return new RealtyUnit(link, price, contact, phoneRef, content);
    }

    public String getImage(Elements href) {
        String classAttr = href.attr("class");
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
