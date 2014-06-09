import email.Emailer;
import email.SimpleEmailerImpl;

import static spark.Spark.*;
/**
 * Date: 6/5/14
 * @author Russ Forstall
 */
public class CoffeeMachine {
    public static void main(String[] args) {
        get("/coffee",(request, response) -> {
            return "Hello coffee";
        });

        get("/test",(request, response) -> {
            return "Hello test";
        });

        get("/mail/:email", (request, response) -> {
            Emailer emailer = new SimpleEmailerImpl();
            String message = "This is gonna be a long message";
            emailer.sendEmail("russ4stall@gmail.com", "the subject", message);

            return "Message sent to " + request.params(":email");
        });
    }

}
