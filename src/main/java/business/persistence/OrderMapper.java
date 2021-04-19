package business.persistence;

import business.exceptions.UserException;

import java.sql.*;

public class OrderMapper {
    private Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }

    public void insertOrder(int userId, Timestamp timestamp, String status) throws UserException {
        try (Connection connection = database.connect())
        {
            String sql = "INSERT INTO `olskerCupcake`.`orders`" +
                    " (`user_id`," +
                    " `created`, " +
                    "`status`)" +
                    " VALUES ('?', '?', '?');";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setInt(1,userId);
                ps.setTimestamp(2,timestamp);
                ps.setString(3,status);

                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int orderId = ids.getInt(1);

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

}
