package services;

import pot.CoffeeType;
import pot.Pot;
import pot.dao.PotDao;
import pot.dao.PotDaoImpl;

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
        PotDao potDao = new PotDaoImpl();
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(PORT);
            System.out.println("BrewListener start listening on port " + PORT + ".");
            while (true) {
                Socket client = listener.accept();
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);

                    String fromClient = in.readLine();
                    System.out.println("received: " + fromClient);

                    if (CoffeeType.isValidCoffeeType(fromClient)) {
                        potDao.addPot(new Pot(CoffeeType.valueOf(fromClient), new Date()));
                        System.out.println("A pot of " + fromClient + " was brewed!");
                    } else {
                        System.out.println("Invalid data from client");
                    }

                } finally {
                    client.close();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        finally {
            try {
            listener.close();
            } catch (IOException e) {
                System.out.println(e.getStackTrace());
            }
        }
    }

}
