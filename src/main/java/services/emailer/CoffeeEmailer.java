package services.emailer;

/**
 * Created by russellf on 8/8/2014.
 */
public interface CoffeeEmailer {
    void sendToAll(String subject, String message);

    void send(String subject, String message);
}
