package business.persistence;

import business.entities.Orderline;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderlineMapper {
    private Database database;

    public OrderlineMapper(Database database) {
        this.database = database;
    }

    public Orderline insertOrderline(int orderId,
                                     int quantity,
                                     double price,
                                     int toppingId,
                                     int bottomId) throws UserException {
        try (Connection connection = database.connect())
        {
            String sql = "INSERT INTO `olskerCupcake`.`orderline`" +
                    " (`order_id`," +
                    " `quantity`," +
                    " `price`," +
                    " `topping_id`, " +
                    "`bottom_id`)" +
                    " VALUES (?,?,?,?,?);";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setInt(1, orderId);
                ps.setInt(2, quantity);
                ps.setDouble(3, price);
                ps.setInt(4, toppingId);
                ps.setInt(5, bottomId);

                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int orderlineId = ids.getInt(1);

                return new Orderline(orderlineId,orderId,quantity,price,toppingId,bottomId);
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

    public List<Orderline> getAllOrderlinesById(int orderId) throws UserException {
        List<Orderline> orderlineList = new ArrayList<>();
        try (Connection connection = database.connect())
        {
            String sql = "SELECT * FROM olskerCupcake.orderline WHERE order_id = " + orderId;

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {

                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int orderlineId = rs.getInt("orderline_id");
                    int quantity = rs.getInt(3);
                    double price = rs.getDouble(4);
                    int toppingId = rs.getInt(5);
                    int bottomId = rs.getInt(6);


             Orderline orderline = new Orderline(orderlineId,orderId,quantity,price,toppingId,bottomId);
             orderlineList.add(orderline);

//TODO: Execute methods to add:
                    //sport
                    //hobby
                    //user
                }
                return orderlineList;
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException("Connection to database could not be established");
        }
    }

}
