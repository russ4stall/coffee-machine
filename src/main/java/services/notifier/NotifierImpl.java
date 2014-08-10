package services.notifier;

import services.emailer.CoffeeEmailer;
import services.emailer.CoffeeEmailerImpl;
import services.twitter.CoffeeTweeter;
import services.twitter.CoffeeTweeterImpl;

/**
 * Created by russellf on 8/8/2014.
 */
public class NotifierImpl implements Notifier{

    @Override
    public void notify(String subject, String message) {
        //send tweet
        CoffeeTweeter tweeter = new CoffeeTweeterImpl();
        tweeter.tweet(message);
        //send email
        CoffeeEmailer emailer = new CoffeeEmailerImpl();
        emailer.sendToAll(subject, message);

        System.out.println(subject);
        System.out.println(message);
    }
}
