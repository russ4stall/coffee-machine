package pot.dao;

import pot.Pot;

import java.util.List;

/**
 * Date: 7/10/14
 * Time: 11:58 AM
 *
 * @author Russ Forstall
 */
public interface PotDao {
    void addPot(Pot pot);

    List<Pot> getAll();

    List<Pot> getAllByType();
}
