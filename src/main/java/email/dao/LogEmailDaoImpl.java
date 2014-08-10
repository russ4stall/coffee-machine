package email.dao;

import coffeemachine.CoffeeMachineDao;
import email.LogEmail;
import util.SqlUtilities;

import java.sql.*;

/**
 * Created by russ on 8/5/14.
 */
public class LogEmailDaoImpl extends CoffeeMachineDao implements LogEmailDao {

    @Override
    public void addLog(LogEmail logEmail) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getName(), dataSource.getPassword());

            String query = "INSERT INTO logemail (email, log, createdOn) " +
                    "VALUES (?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, logEmail.getEmail());
            preparedStatement.setString(2, logEmail.getLog());
            preparedStatement.setTimestamp(3, new Timestamp(logEmail.getCreatedOn().getTime()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }
    }
}
