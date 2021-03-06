package email.dao;

import coffeemachine.CoffeeMachineDao;
import email.Email;
import util.SqlUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 7/10/14
 * Time: 12:07 PM
 *
 * @author Russ Forstall
 */
public class EmailDaoImpl extends CoffeeMachineDao implements EmailDao {

    @Override
    public void addEmail(Email email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getName(), dataSource.getPassword());

            String query = "INSERT INTO email (email, createdOn) " + "VALUES (?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email.getEmailAddress());
            preparedStatement.setTimestamp(2, new Timestamp(email.getCreatedOn().getTime()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }
    }

    @Override
    public Email getEmail(String email) {
        return null;
    }

    @Override
    public boolean emailExists(Email email) {
        boolean emailExists = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getName(), dataSource.getPassword());

            preparedStatement = connection.prepareStatement("SELECT count(1) FROM email WHERE email=?");
            preparedStatement.setString(1, email.getEmailAddress());
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            emailExists = resultSet.getInt("count(1)") == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }
        return emailExists;
    }

    @Override
    public void removeEmail(Email email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getName(), dataSource.getPassword());

            String query = "DELETE FROM email WHERE email = ? ";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email.getEmailAddress());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }
    }

    @Override
    public List<Email> getMailingList() {
        List<Email> emailList = new ArrayList<Email>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //SqlUtilities.jbdcUtil();
        String query = null;
        try {
            connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getName(), dataSource.getPassword());

            query = "SELECT * FROM email";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Email email = new Email(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getDate("createdOn"));

                emailList.add(email);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
            SqlUtilities.closeResultSet(resultSet);
        }

        return emailList;
    }
}
