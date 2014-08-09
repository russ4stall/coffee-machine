package services.messages;

/**
 * Created by russ on 8/8/14.
 */
public class MessageMakerFactory {
    private MessageMaker messageMaker;

    public MessageMakerFactory(MessageMaker messageMaker) {
        this.messageMaker = messageMaker;
    }

    public MessageMaker getMessageMaker() {
        return messageMaker;
    }
}
