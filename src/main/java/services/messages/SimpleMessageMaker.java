package services.messages;

import pot.CoffeeType;

/**
 * Created by russ on 8/9/14.
 */
public class SimpleMessageMaker implements MessageMaker {
    @Override
    public String createMessage(CoffeeType coffeeType) {
        return "Come and get it! A pot of " + coffeeType.display() +
                " is being brewed.";
    }

    @Override
    public String createSubject(CoffeeType coffeeType) {
        return coffeeType.display() + " coffee's on!";
    }
}
