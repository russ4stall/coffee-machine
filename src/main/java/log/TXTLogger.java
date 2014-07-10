package log;

import email.Email;
import externaldata.SubscribeAction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Date: 6/18/14
 * Time: 1:34 PM
 *
 * @author Russ Forstall
 */
public class TXTLogger {
    private String filePath;

    public void log(String log) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
            out.println(log);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
