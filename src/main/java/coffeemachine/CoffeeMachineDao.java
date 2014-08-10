package coffeemachine;

import util.DataSource;

/**
 * Created by russ on 8/10/14.
 */
public class CoffeeMachineDao {
    //protected ApplicationScope applicationScope = ApplicationScope.getInstance();
    protected DataSource dataSource = CoffeeConfig.DATA.getDataSource();

}
