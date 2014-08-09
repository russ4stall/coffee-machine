package services;

import pot.CoffeeType;
import pot.Pot;
import pot.dao.PotDao;
import pot.dao.PotDaoImpl;
import services.messages.MessageMaker;
import services.messages.MessageMakerFactory;
import services.messages.SimpleMessageMaker;
import services.notifier.Notifier;
import services.notifier.NotifierFactory;
import services.notifier.NotifierImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Date: 6/13/14
 * Time: 5:00 PM
 *
 * @author Russ Forstall
 */
public class BrewListenerRunnable implements Runnable {
    private static final int PORT = 8989;

    @Override
    public void run() {
        MessageMakerFactory messageMakerFactory = new MessageMakerFactory(new SimpleMessageMaker());
        NotifierFactory notifierFactory = new NotifierFactory(new NotifierImpl());


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
                        coffeePotTask.setMessageMaker(messageMakerFactory);
                        coffeePotTask.setNotifier(notifierFactory);
                        coffeePotTask.setPotDao(new PotDaoImpl());
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
