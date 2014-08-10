package pot.dao;

import coffeemachine.CoffeeMachineDao;
import email.Email;
import pot.CoffeeType;
import pot.Pot;
import util.SqlUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 7/10/14
 * Time: 12:08 PM
 *
 * @author Russ Forstall
 */
public class PotDaoImpl extends CoffeeMachineDao implements PotDao {
    @Override
    public void addPot(Pot pot) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getName(), dataSource.getPassword());

            String query = "INSERT INTO pot (type, brewedOn) " +
                    "VALUES (?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pot.getType().toString());
            preparedStatement.setTimestamp(2, new Timestamp(pot.getBrewedOn().getTime()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }
    }

    @Override
    public List<Pot> getAll() {
        List<Pot> potList = new ArrayList<Pot>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //SqlUtilities.jbdcUtil();
        String query = null;
        try {
            connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getName(), dataSource.getPassword());

            query = "SELECT * FROM pot";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Pot pot = new Pot(
                        resultSet.getInt("id"),
                        CoffeeType.valueOf(resultSet.getString("type")),
                        resultSet.getDate("createdOn")
                );

                potList.add(pot);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
            SqlUtilities.closeResultSet(resultSet);
        }

        return potList;
    }

    @Override
    public List<Pot> getAllByType(CoffeeType coffeeType) {
        List<Pot> potList = new ArrayList<Pot>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //SqlUtilities.jbdcUtil();
        String query = null;
        try {
            connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getName(), dataSource.getPassword());

            query = "SELECT * FROM pot WHERE type = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, coffeeType.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Pot pot = new Pot(
                        resultSet.getInt("id"),
                        CoffeeType.valueOf(resultSet.getString("type")),
                        resultSet.getDate("createdOn")
                );

                potList.add(pot);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
            SqlUtilities.closeResultSet(resultSet);
        }

        return potList;
    }
}
