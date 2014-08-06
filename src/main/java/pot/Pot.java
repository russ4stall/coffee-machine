package pot;

import java.util.Date;

/**
 * Date: 7/10/14
 * Time: 11:46 AM
 *
 * @author Russ Forstall
 */
public class Pot {
    private int id;
    private CoffeeType type;
    private Date brewedOn;

    public Pot() {
    }

    public Pot(CoffeeType type, Date brewedOn) {
        this.type = type;
        this.brewedOn = brewedOn;
    }

    public Pot(int id, CoffeeType type, Date brewedOn) {
        this.id = id;
        this.type = type;
        this.brewedOn = brewedOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CoffeeType getType() {
        return type;
    }

    public void setType(CoffeeType type) {
        this.type = type;
    }

    public Date getBrewedOn() {
        return brewedOn;
    }

    public void setBrewedOn(Date brewedOn) {
        this.brewedOn = brewedOn;
    }
}
