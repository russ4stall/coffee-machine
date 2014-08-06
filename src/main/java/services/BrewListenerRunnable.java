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

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Date: 6/13/14
 * Time: 5:00 PM
 *
 * @author Russ Forstall
 */
public class BrewListenerRunnable implements Runnable {

    @Override
    public void run() {
        PotDao potDao = new PotDaoImpl();
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

                    if (!isEmpty(fromClient)) {
                        for (CoffeeType type : CoffeeType.values()) {
                            if (type.name().equals(fromClient)) {
                                System.out.println(fromClient);
                            }
                        }
                    }

                    if(fromClient.equals(CoffeeType.COLUMBIAN.toString())) {
                        System.out.println("ees columbian coffee");
                        potDao.addPot(new Pot(CoffeeType.COLUMBIAN, new Date()));
                    } else if (fromClient.equals(CoffeeType.DONUT_SHOP.toString())) {
                        System.out.println("da donut shop coffee");
                        potDao.addPot(new Pot(CoffeeType.DONUT_SHOP, new Date()));
                    } else if (fromClient.equals(CoffeeType.FLAVORED_GOODNESS.toString())){
                        System.out.println("mmm flavored goodness");
                        potDao.addPot(new Pot(CoffeeType.FLAVORED_GOODNESS, new Date()));
                    } else if (fromClient.equals(CoffeeType.UNKNOWN.toString())) {
                        System.out.println("mmm coffee!");
                        potDao.addPot(new Pot(CoffeeType.UNKNOWN, new Date()));
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
