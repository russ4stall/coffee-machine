package services;

import pot.CoffeeType;
import pot.Pot;
import pot.dao.PotDao;
import services.messages.MessageMaker;
import services.messages.MessageMakerFactory;
import services.notifier.Notifier;
import services.notifier.NotifierFactory;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by russ on 8/8/14.
 */
public class CoffeePotTask{
    private CoffeeType coffeeType;

    private MessageMaker messageMaker;
    private Notifier notifier;
    private PotDao potDao;

    public CoffeePotTask() {}

    public void executeTask() {
        potDao.addPot(new Pot(coffeeType, new Date()));

        String message = messageMaker.createMessage(coffeeType);
        notifier.notify(coffeeType.display() + " coffee's on", message);
    }

    public void setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }

    public void setMessageMaker(MessageMakerFactory messageMakerFactory) {
        this.messageMaker = messageMakerFactory.getMessageMaker();
    }

    public void setMessageMaker(MessageMaker messageMaker) {
        this.messageMaker = messageMaker;
    }

    public void setNotifier(NotifierFactory notifierFactory) {
        this.notifier = notifierFactory.getNotifier();
    }

    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    public void setPotDao(PotDao potDao) {
        this.potDao = potDao;
    }
}
