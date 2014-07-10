package services;

import pot.CoffeeType;

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

    @Override
    public void run() {

        ServerSocket listener = null;
        try {
            listener = new ServerSocket(8989);
            while (true) {
                Socket client = listener.accept();
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(client.getOutputStream(),true);

                    String fromClient = in.readLine();
                    System.out.println("received: " + fromClient);

                    if(fromClient.equals(CoffeeType.COLUMBIAN.toString())) {
                        System.out.println("ees columbian coffee");
                    } else if (fromClient.equals(CoffeeType.DONUT_SHOP.toString())) {
                        System.out.println("da donut shop coffee");
                    } else if (fromClient.equals(CoffeeType.FLAVORED_GOODNESS.toString())){
                        System.out.println("mmm flavored goodness");
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
