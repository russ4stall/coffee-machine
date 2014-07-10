package log;

import email.Email;
import externaldata.SubscribeAction;

import java.util.Date;

/**
 * Date: 6/13/14
 * Time: 4:19 PM
 *
 * @author Russ Forstall
 */
public interface Logger {

    void log(Email email, SubscribeAction action);

}
