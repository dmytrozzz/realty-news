package com.dmytro.realty.engine;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class SendMan {
    HtmlEmail email;

    public void createMessage(List<RealtyOffer> units) {
	email = new HtmlEmail();
	email.setHostName("smtp.gmail.com");
	email.setSmtpPort(587);
	email.setAuthenticator(new DefaultAuthenticator("krepka.klepka@gmail.com", "oprotvereziynyk123"));
	email.setStartTLSEnabled(true);

	email.setSubject("Realty Service Feed");
	email.setCharset("UTF-8");
	String htmlContent = "<html>";
	for (RealtyOffer unit : units) {
	    URL url;
	    String cid = "Без телефону";
	    try {
		url = new URL(unit.getPhoneRef());
		cid = email.embed(url, "Phone" + unit.hashCode());
	    } catch (MalformedURLException e) {
		System.out.println("Wrong or no telephone!!!");
	    } catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    htmlContent += format(unit, cid);
	}
	htmlContent += "</html>";
	try {
	    email.setFrom("realtyhelper@realtyservice.com.ua", "Your Realty Helper");
	    email.setHtmlMsg(htmlContent);
	    // set the alternative message
	    email.setTextMsg("Your email client does not support HTML messages");
	} catch (EmailException emae) {
	    emae.printStackTrace();
	}
    }

    public void addRecipient(String userEmail) {
	try {
	    email.addTo(userEmail);
	} catch (EmailException e) {
	    System.out.println("Missed " + userEmail);
	    e.printStackTrace();
	}
    }

    public void sendEmail() {
	try {
	    email.send();
	} catch (EmailException e) {
	    e.printStackTrace();
	}
    }

    private String format(RealtyOffer unit, String imageCid) {
	return unit.getOfferContent() + "<h5>Ціна: " + unit.getPrice() + " |Ініціатор: " + unit.getOffender()
		+ " | Телефон: <img src=\"cid:" + imageCid + "\"> | <a href='" + unit.getLink()
		+ "'>Посилання</a></h5>-------------------------------------------------------------------------<br/>";
    }
}
