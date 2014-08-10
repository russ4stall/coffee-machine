import coffeemachine.ApplicationScope;
import coffeemachine.CoffeeMachineStartUp;
import email.Email;
import email.LogEmail;
import email.LogEmailAction;
import email.dao.EmailDao;
import email.dao.EmailDaoImpl;
import email.dao.LogEmailDao;
import email.dao.LogEmailDaoImpl;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import services.BrewListenerRunnable;


import java.util.Date;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static spark.Spark.*;
/**
 * Date: 6/5/14
 * @author Russ Forstall
 */
public class CoffeeMachine {
    static Logger log = Logger.getLogger(CoffeeMachine.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        log.info("umm... app has started");

        CoffeeMachineStartUp setUp = new CoffeeMachineStartUp();
        setUp.execute();

        //service for listening for button presses
        Thread thread = new Thread(new BrewListenerRunnable());
        thread.start();

        //point Spark to static resources
        staticFileLocation("/public");

        //instantiate database access objects
        EmailDao emailDao = new EmailDaoImpl();
        LogEmailDao logEmailDao = new LogEmailDaoImpl();

        //ROUTES
        post("/add", (request, response) -> {
           //validate
            if (isEmpty(request.queryParams("email"))){
                return "Error: empty email not allowed.";
            }
            EmailValidator emailValidator = EmailValidator.getInstance();
            if (!emailValidator.isValid(request.queryParams("email"))) {
                return request.queryParams("email") + " is not a valid email address!";
            }

            Email email = new Email(request.queryParams("email"));
            if (emailDao.emailExists(email)) {
                return email.getEmailAddress() + " is already subscribed!";
            }
            emailDao.addEmail(email);
            logEmailDao.addLog(new LogEmail(email.getEmailAddress(), LogEmailAction.SUBSCRIBED.toString(), new Date()));

            return request.queryParams("email") + " was successfully added to the list!";
        });

        post("/unsubscribe", (request, response) -> {
            //validate
            if (isEmpty(request.queryParams("email"))) {
                return "Error: empty email not allowed.";
            }
            EmailValidator emailValidator = EmailValidator.getInstance();
            if (!emailValidator.isValid(request.queryParams("email"))) {
                return request.queryParams("email") + " is not a valid email address!";
            }

            Email email = new Email(request.queryParams("email"));
            if (!emailDao.emailExists(email)) {
                return "Error: email doesn't exist.";
            }
            emailDao.removeEmail(email);
            logEmailDao.addLog(new LogEmail(email.getEmailAddress(), LogEmailAction.UNSUBSCRIBED.toString(), new Date()));
            return request.queryParams("email") + " successfully unsubscribed.";
        });
    }
}
