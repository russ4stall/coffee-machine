package externaldata;

import java.util.List;

/**
 * Date: 6/12/14
 * Time: 11:16 AM
 *
 * @author Russ Forstall
 */
public interface ExternalMailingList {

    void update(List<String> list);

    List<String> load();
}
