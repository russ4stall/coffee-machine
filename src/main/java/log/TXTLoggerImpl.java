package log;

import email.Email;
import externaldata.SubscribeAction;
import log.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Date: 6/13/14
 * Time: 4:22 PM
 *
 * @author Russ Forstall
 */
public class TXTLoggerImpl implements Logger {
    @Override
    public void log(Email email, SubscribeAction action) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/subscribe-log.txt", true)));
            out.println(email.getEmailAddress() + " " + action.toString() + " on " + email.getCreatedOn());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
