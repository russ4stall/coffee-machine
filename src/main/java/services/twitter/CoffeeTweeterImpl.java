package services.twitter;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by russellf on 8/7/2014.
 */
public class CoffeeTweeterImpl implements CoffeeTweeter {
    @Override
    public void tweet(String message) {

        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("XyIFTpNfuRnf5qmhVsNrnlVZJ")
                    .setOAuthConsumerSecret("BJ4oCMEkgbIH30B9hZbcnPXIxzGwi6ij3kbssSMcp0vyIwM8cy")
                    .setOAuthAccessToken("2461767216-IS0kZ9u80SztDaX1RgVeRFgfUoGwjU63bBeU2xC")
                    .setOAuthAccessTokenSecret("sQzm1UmuJuzbdmltsqm9m9Ron2sJK2CJTtkfeKNQlVjGN");
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();

            Status status = twitter.updateStatus(message);
        } catch (TwitterException e) {
            e.printStackTrace();
            System.out.println(e);
        }


    }
}
