package coffeemachine;

import util.DataSource;

/**
 * Created by russ on 8/10/14.
 */
public enum CoffeeConfig {
    DATA;

    private DataSource dataSource = new DataSource("jdbc:mysql://localhost:3306/coffeemachine", "coffee", "coffee");

    public DataSource getDataSource() {
        return dataSource;
    }
}
