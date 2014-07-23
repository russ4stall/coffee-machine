import com.google.gson.Gson;
import email.Email;
import email.dao.EmailDao;
import email.dao.EmailDaoImpl;
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
        //ON STARTUP
        EmailDao emailDao = new EmailDaoImpl();
        //STATIC FILES
        staticFileLocation("/public"); // Static files

        //Service listening for button presses
        Thread thread = new Thread(new BrewListenerRunnable());
        thread.start();

        //ROUTES
        get("/mailingList", (request, response) -> {

            List<Email> emailList = emailDao.getMailingList();

            StringBuilder stringBuilder = new StringBuilder();
            Gson gson = new Gson();

            for (Email email : emailList) {
                gson.toJson(email.getEmailAddress(), stringBuilder);
            }

            return stringBuilder;
        });


        post("/add", (request, response) -> {
            if (isEmpty(request.queryParams("email"))){
                return "Error: empty email not allowed.";
            }

            Email email = new Email();
            if (!email.setEmailAddress(request.queryParams("email"))) {
                return request.queryParams("email") + " is not a valid email address!";
            }

            if (emailDao.emailExists(email)) {
                return email.getEmailAddress() + " is already subscribed!";
            }
            email.setCreatedOn(new Date());
            emailDao.addEmail(email);

            return request.queryParams("email") + " was successfully added to the list!";
        });

        post("/unsubscribe", (request, response) -> {
            if (isEmpty(request.queryParams("email"))) {
                return "Error: empty email not allowed.";
            }
            Email email = new Email();
            if (!email.setEmailAddress(request.queryParams("email"))) {
                return request.queryParams("email") + " is not a valid email address!";
            }
            if (!emailDao.emailExists(email)) {
                return "Error: email doesn't exist.";
            }
            emailDao.unsubscribe(email);
            return request.queryParams("email") + " successfully unsubscribed.";
        });


    }
}
