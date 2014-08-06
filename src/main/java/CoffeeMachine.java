import com.google.gson.Gson;
import email.Email;
import email.LogEmail;
import email.LogEmailAction;
import email.dao.EmailDao;
import email.dao.EmailDaoImpl;
import email.dao.LogEmailDao;
import email.dao.LogEmailDaoImpl;
import org.apache.commons.validator.routines.EmailValidator;
import org.flywaydb.core.Flyway;
import services.BrewListenerRunnable;


import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static spark.Spark.*;
/**
 * Date: 6/5/14
 * @author Russ Forstall
 */
public class CoffeeMachine {
    public static void main(String[] args) {
        //SETUP
        EmailDao emailDao = new EmailDaoImpl();
        LogEmailDao logEmailDao = new LogEmailDaoImpl();

        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:mysql://localhost:3306/coffeemachine", "coffee", "coffee");
        flyway.migrate();

        //STATIC FILES
        staticFileLocation("/public"); // Static files

        //Service listening for button presses
        Thread thread = new Thread(new BrewListenerRunnable());
        thread.start();

        //ROUTES
        get("/mailinglist", (request, response) -> {
            List<Email> emailList = emailDao.getMailingList();
            StringBuilder stringBuilder = new StringBuilder();
            for (Email email : emailList) {
               stringBuilder.append(email.getEmailAddress() + "<br/>");
            }
            return stringBuilder;
        });


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
