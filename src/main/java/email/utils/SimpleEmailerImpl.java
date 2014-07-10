package email.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Date: 6/6/14
 * Time: 9:14 AM
 *
 * @author Russ Forstall
 */
public class SimpleEmailerImpl implements Emailer {

    @Override
    public void sendEmail(String recipient, String subject, String message) {

        Properties props = new Properties();
        /*props.put("mail.smtp.host", "192.168.2.41");*/
        //props.put("mail.smtp.host", "mailtrap.io");
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "25");
        //props.put("mail.smtp.user", "21102079b534f03b8");
        //props.put("mail.smtp.password", "cdb23e09b8be61");

        Session session = Session.getDefaultInstance(props, null);

        String footer = "\n \n \n \nKeep calm and drink coffee.";

        String msgBody = message + footer;

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("coffeemachine@infinity-kitchen.com", "Coffee Pot"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(recipient));
            msg.setSubject(subject);
            //msg.setText(msgBody);
            msg.setContent(msgBody, "text/html");
            Transport.send(msg);

        } catch (AddressException e) {
            System.out.println(e);
        } catch (MessagingException e) {
            System.out.println(e);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }
}
