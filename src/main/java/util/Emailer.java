package util;

import javax.mail.Session;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Date: 6/6/14
 * Time: 9:10 AM
 *
 * @author Russ Forstall
 */
public interface Emailer {

    void sendEmail(String recipient, String subject, String message);

}
