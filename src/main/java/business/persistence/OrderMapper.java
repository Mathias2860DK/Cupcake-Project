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

    public int insertOrder(int userId, Timestamp timestamp, String status) throws UserException {
        try (Connection connection = database.connect()) {

            String sql = "INSERT INTO `orders`" +
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


return orderId;
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
            String sql = "SELECT * FROM orders;";

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

    public List<Orders> getOrdersByUserId(int userId) throws UserException {
        List<Orders> orderListById = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM orders where user_id = "+userId +";";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("order_id");
                    int userID = rs.getInt("user_id");
                    Timestamp created = rs.getTimestamp("created");
                    String status = rs.getString("status");

                    Orders orders = new Orders(orderID, userID, created, status);
                    orderListById.add(orders);



//TODO: Execute methods to add:
                }
                return orderListById;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public int getOrderIdByUserIdAndStatus(int userId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM olskerCupcake.orders where user_id = "+userId +" and status = 'In basket'";
int orderID = 0;

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    orderID = rs.getInt("order_id");

                }
                return orderID;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
    public int deleteOrder(int orderId) throws UserException {
        try (Connection connection = database.connect())
        {
            //Virker ikke pga man ikke kan slette et sport_id de bliver brugt i BMI entry tablen.
            // String sql = "DELETE FROM `bmi`.`sport` WHERE sport_id = ?;";

            String sql = "DELETE FROM orders WHERE `order_id` = " + orderId + ";";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {

                int rowsAffected = ps.executeUpdate();
                return rowsAffected;

            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException | UserException ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

    }



