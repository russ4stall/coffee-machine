package services.messages;

import org.joda.time.DateTime;
import pot.CoffeeType;

/**
 * Created by russ on 8/9/14.
 */
public class TimeAwareMessageMaker implements MessageMaker {
    @Override
    public String createMessage(CoffeeType coffeeType) {
        String message = "The time is " + DateTime.now().toString() +
                " and a pot of " + coffeeType.display() +
                " is being brewed.";
        return message;
    }

    @Override
    public String createSubject(CoffeeType coffeeType) {
        return null;
    }
}
