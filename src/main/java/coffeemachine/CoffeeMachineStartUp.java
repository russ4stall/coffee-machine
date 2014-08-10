package coffeemachine;

import coffeemachine.ApplicationScope;
import org.flywaydb.core.Flyway;
import util.DataSource;

/**
 * Created by russ on 8/10/14.
 */
public class CoffeeMachineStartUp {


    public void execute() {
        //ApplicationScope applicationScope = ApplicationScope.getInstance();
        DataSource dataSource = CoffeeConfig.DATA.getDataSource();

        //schema migration
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource.getUrl(), dataSource.getName(), dataSource.getPassword());
        flyway.migrate();



    }


}
