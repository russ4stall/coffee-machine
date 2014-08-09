package services.messages;

import pot.CoffeeType;

/**
 * Created by russ on 8/8/14.
 */
public interface MessageMaker {
    String createMessage(CoffeeType coffeeType);

    String createSubject(CoffeeType coffeeType);
}
