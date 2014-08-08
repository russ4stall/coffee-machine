package pot;

/**
 * Date: 7/10/14
 * Time: 11:47 AM
 *
 * @author Russ Forstall
 */
public enum CoffeeType {
    COLUMBIAN, DONUT_SHOP, FLAVORED_GOODNESS, UNKNOWN;

    public static final boolean isValidCoffeeType(String s) {
        for (CoffeeType type : CoffeeType.values()) {
            if (s.equals(type.toString())) {
                return true;
            }
        }
        return false;
    }

    public String display() {
        if (this.equals(COLUMBIAN)) {
            return "Columbian";
        } else if (this.equals(DONUT_SHOP)) {
            return "Donut Shop";
        } else if (this.equals(FLAVORED_GOODNESS)) {
            return "Flavored Goodness";
        } else {
            return "UNKNOWN";
        }
    }
}