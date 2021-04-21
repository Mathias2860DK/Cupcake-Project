package business.persistence;

import business.entities.Orderline;
import business.exceptions.UserException;

import java.sql.*;
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

}
