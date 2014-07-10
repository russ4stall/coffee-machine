package externaldata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Date: 6/12/14
 * Time: 11:20 AM
 *
 * @author Russ Forstall
 */
public class TXTExternalMailingListImpl implements ExternalMailingList {

    @Override
    public void update(List<String> list) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/mailing-list.txt", false)));
            for (String email : list) {
                out.println(email);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> load() {
        List<String> list = new ArrayList<String>();
            try {
                Scanner s = new Scanner(new File("data/mailing-list.txt"));
                while (s.hasNext()){
                    list.add(s.next());
                }
                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return list;
    }
}
