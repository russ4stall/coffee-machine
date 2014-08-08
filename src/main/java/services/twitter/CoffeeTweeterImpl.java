package services.twitter;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Created by russellf on 8/7/2014.
 */
public class CoffeeTweeterImpl implements CoffeeTweeter {
    @Override
    public void tweet(String message) {

        try {
            Twitter twitter = TwitterFactory.getSingleton();
            Status status = twitter.updateStatus(message);
        } catch (TwitterException e) {
            e.printStackTrace();
            System.out.println(e);
        }


    }
}
