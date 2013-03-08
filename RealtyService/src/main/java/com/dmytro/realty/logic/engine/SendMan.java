package com.dmytro.realty.logic.engine;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendMan {
    public static String smtpHost = "smtp.gmail.com";
    public static String login = "krepka.klepka@gmail.com";
    public static String pass = "oprotvereziynyk123";
    Properties props;

    public SendMan() {
	props = new Properties();
	props.setProperty("mail.transport.protocol", "smtp");
	props.setProperty("mail.host", smtpHost);
	props.setProperty("mail.user", "krapka.klepka@gmail.com");
	props.setProperty("mail.password", "oprotvereziynyk123");
	props.setProperty("mail.smtp.auth", "true");
	props.setProperty("mail.smtp.port", "" + 587);
	props.setProperty("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", smtpHost);
    }

    private Message messageFactoryMethod(String from, String to, String content, Session session)
	    throws MessagingException {
	Message msg = new MimeMessage(session);
	msg.setFrom(new InternetAddress(from));
	InternetAddress[] address = { new InternetAddress(to) };
	msg.setRecipients(Message.RecipientType.TO, address);
	msg.setSubject("Slando realty news");
	msg.setSentDate(new Date());
	msg.setText(content);
	return msg;
    }

    public void sendNews(String to, String content) {
	sendMail(to, "news@java.net", smtpHost, content);
    }

    private void sendMail(String to, String from, String smtpHost, String content) {
	System.out.println("Sending mail to " + to);
	Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	    protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(login, pass);
	    }
	});

	try {
	    Message msg = messageFactoryMethod(from, to, content, session);
	    Transport.send(msg);
	} catch (MessagingException mex) {
	    mex.printStackTrace();
	}
    }
}
