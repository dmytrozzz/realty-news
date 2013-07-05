package com.dmytro.realty.engine;

import com.dmytro.realty.domain.RealtyOffer;
import com.dmytro.realty.domain.RealtyUser;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

public class SendMan {
    HtmlEmail email;

    private static HtmlEmail createEmail() {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator(
                "krepka.klepka@gmail.com", "oprotvereziynyk123"));
        email.setStartTLSEnabled(true);

        email.setSubject("Realty Service Feed");
        email.setCharset("UTF-8");
        return email;
    }

    public static void sendMessage(RealtyUser user) {
        HtmlEmail email = createEmail();
        String htmlContent = "<html>";
        htmlContent += "<h4>Ваш логін: </h4><h1>" + user.getLogin() + "</h1>";
        htmlContent += "<h4>Ваш пароль: </h4><h1>" + user.getPassword() + "</h1>";
        htmlContent += "</html>";
        try {
            email.setFrom("realtyhelper@realtyservice.com.ua", "Your Realty Helper");
            email.setHtmlMsg(htmlContent);
            // set the alternative message
            email.setTextMsg("Your email client does not support HTML messages");
            email.addTo(user.getEmail());
            email.send();
        } catch (EmailException emae) {
            emae.printStackTrace();
        }
    }

    public static void sendNoOffersMessage(Collection<RealtyUser> users) {
        HtmlEmail email = createEmail();
        String htmlContent = "<html>";
        htmlContent += "<h3>За вашими критеріями нічого не знайдено. Спробуйте підібрати щось реалістичніше): </h3>";
        htmlContent += "</html>";
        try {
            email.setFrom("realtyhelper@realtyservice.com.ua", "Your Realty Helper");
            email.setHtmlMsg(htmlContent);
            // set the alternative message
            email.setTextMsg("Your email client does not support HTML messages");
            for (RealtyUser user : users)
                email.addTo(user.getEmail());
            email.send();
        } catch (EmailException emae) {
            emae.printStackTrace();
        }
    }

    public void createMessage(List<RealtyOffer> units) {
        email = createEmail();

        String htmlContent = "<html>";
        for (RealtyOffer unit : units) {
            URL url;
            String phone = unit.getPhone();
            boolean phoneAsIs = true;
            //if phone is image
            try {
                url = new URL(unit.getPhone());
                phone = email.embed(url, "Phone" + unit.hashCode());
                phoneAsIs = false;
            } catch (MalformedURLException e) {
                //System.out.println("Wrong or no telephone!!!");
            } catch (EmailException e) {
                e.printStackTrace();
            }
            htmlContent += format(unit, phone, phoneAsIs);
        }
        htmlContent += "</html>";
        try {
            email.setFrom("realtyhelper@realtyservice.com.ua",
                    "Your Realty Helper");
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

    private String format(RealtyOffer unit, String phone, boolean phoneAsIs) {
        return unit.getOfferContent()
                + "<h5>Ціна: "
                + unit.getPrice()
                + " |Додано: "
                + unit.getDate()
                + " |Ініціатор: "
                + unit.getOffender()
                + " | Телефон: + "
                + (phoneAsIs ? phone : "<img src=\"cid:" + phone + "\">")
                + " | <a href='"
                + unit.getLink()
                + "'>Посилання</a></h5>-------------------------------------------------------------------------<br/>";
    }
}
