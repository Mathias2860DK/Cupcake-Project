package business.persistence;

import business.entities.Orders;
import business.exceptions.UserException;

import javax.persistence.criteria.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    private Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }

    public void insertOrder(int userId, Timestamp timestamp, String status) throws UserException {
        try (Connection connection = database.connect()) {

            String sql = "INSERT INTO `olskerCupcake`.`orders`" +
                    " (`user_id`," +
                    " `created`, " +
                    "`status`)" +
                    " VALUES (?,?,?);";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, userId);
                ps.setTimestamp(2, timestamp);
                ps.setString(3, status);


                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int orderId = ids.getInt(1);

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public List<Orders> getAllOrders() throws UserException {
        List<Orders> orderList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM olskerCupcake.orders;";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("order_id");
                    int userID = rs.getInt("user_id");
                    Timestamp created = rs.getTimestamp("created");
                    String status = rs.getString("status");

                    Orders orders = new Orders(orderID, userID, created, status);
                    orderList.add(orders);


//TODO: Execute methods to add:
                }
                return orderList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
}


