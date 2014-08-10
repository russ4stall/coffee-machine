package services;

import email.dao.EmailDao;
import email.dao.EmailDaoImpl;
import pot.CoffeeType;
import pot.dao.PotDao;
import pot.dao.PotDaoImpl;
import coffeemachine.ApplicationScope;
import services.messages.MessageMaker;
import services.messages.SimpleMessageMaker;
import services.notifier.Notifier;
import services.notifier.NotifierImpl;

import javax.management.NotificationFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Date: 6/13/14
 * Time: 5:00 PM
 *
 * @author Russ Forstall
 */
public class BrewListenerRunnable implements Runnable {
    private static final int PORT = 8989;
    private PotDao potDao = new PotDaoImpl();
    private MessageMaker messageMaker = new SimpleMessageMaker();
    private Notifier notifier = new NotifierImpl();

    @Override
    public void run() {
        try (ServerSocket listener = new ServerSocket(PORT)) {
            System.out.println("BrewListener started listening on port " + PORT + ".");
            while (true) {
                try (Socket client = listener.accept()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String fromClient = in.readLine();
                    System.out.println("BrewListener(on port:" + PORT + " ) received: " + fromClient);

                    if (CoffeeType.isValidCoffeeType(fromClient)) {
                        CoffeePotTask coffeePotTask = new CoffeePotTask();
                        coffeePotTask.setCoffeeType(CoffeeType.valueOf(fromClient));
                        coffeePotTask.setMessageMaker(messageMaker);
                        coffeePotTask.setNotifier(notifier);
                        coffeePotTask.setPotDao(potDao);
                        coffeePotTask.executeTask();

                        System.out.println("A pot of " + fromClient + " was brewed!");
                    } else {
                        System.out.println("Invalid data from client");
                    }

                }
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

}
