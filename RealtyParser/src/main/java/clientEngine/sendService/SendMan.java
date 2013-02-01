package clientEngine.sendService;

import clientEngine.realtyService.RealtyConstants;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
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

    private Message messageFactoryMethod(String from, String to, String content, Session session) throws MessagingException {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        InternetAddress[] address = {new InternetAddress(to)};
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject("Slando realty news");
        msg.setSentDate(new Date());
        msg.setText(content);
        return msg;
    }

    public void sendNews(String content, int lookFor) {
        if (lookFor == RealtyConstants.ROOMS)
            sendMail("nbayur@ukr.net", "news@java.net", smtpHost, content);
        sendMail("krepka.klepka@gmail.com", "news@java.net", smtpHost, content);
//        if (lookFor == RealtyConstants.APARTMENTS)
//            sendMail("mak7.kr@gmail.com", "news@java.net", smtpHost, content);
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

    private void downloadImage(String imageUrl) throws IOException {
        URL website = new URL("http://www.website.com/information.asp");
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("image.html");
        fos.getChannel().transferFrom(rbc, 0, 1 << 24);
        //org.apache.commons.io.FileUtils.copyURLToFile(URL, File)
    }

    private void formContent(){

    }
}
