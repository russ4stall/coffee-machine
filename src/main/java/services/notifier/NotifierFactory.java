package services.notifier;

/**
 * Created by russ on 8/8/14.
 */
public class NotifierFactory {
    private Notifier notifier;

    public NotifierFactory(Notifier notifier) {
        this.notifier = notifier;
    }

    public Notifier getNotifier() {
        return notifier;
    }
}
