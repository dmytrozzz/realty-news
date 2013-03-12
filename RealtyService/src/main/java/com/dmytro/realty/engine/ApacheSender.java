package com.dmytro.realty.engine;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class ApacheSender {
    HtmlEmail email;

    public void createMessage(List<RealtyUnit> units) throws EmailException {
	email = new HtmlEmail();
	email.setHostName("smtp.gmail.com");
	email.setFrom("me@apache.org", "Me");
	email.setSubject("Test email with inline image");
	String htmlContent = "<html>";
	for (RealtyUnit unit : units) {
	    // embed the image and get the content id
	    URL url;
	    try {
		url = new URL(unit.getPhoneRef());
	    } catch (MalformedURLException e) {
		e.printStackTrace();
		continue;
	    }
	    String cid = email.embed(url, "Phone" + unit.hashCode());
	    htmlContent += "<h4>" + unit.getOfferContent() + "</h4><br/>" + "<h5>Ціна: " + unit.getPrice() + "<h5>"
		    + "|<h5>Ініціатор: " + unit.getOffender() + "<h5>" + "|<h5>Телефон:<h5><img src=\"cid:" + cid
		    + "\"><br/>-------------------------------------------------------------------------<br/>"; //+ "<br/><a href=\"" + unit.getLink() + "\">Посилання на оголошення</a>";
	}
	htmlContent += "</html>";
	email.setHtmlMsg(htmlContent);
	// set the alternative message
	email.setTextMsg("Your email client does not support HTML messages");
    }

    public void addRecipient(String userEmail) {
	try {
	    email.addTo(userEmail);
	} catch (EmailException e) {
	    System.out.println("Missed " + userEmail);
	    e.printStackTrace();
	}
    }

    public void sendEmail() throws EmailException {
	email.send();
    }
}
