package business.persistence;

import business.exceptions.UserException;

import java.sql.*;
import java.util.List;

public class OrderlineMapper {
    private Database database;

    public OrderlineMapper(Database database) {
        this.database = database;
    }

    public void insertOrderline(int orderId,
                                int quantity,
                                double price,
                                int toppingId,
                                int bottomId) throws UserException {
        try (Connection connection = database.connect())
        {
            String sql = "INSERT INTO `olskerCupcake`.`orderline`" +
                    " (`quantity`," +
                    " `price`," +
                    " `topping_id`, " +
                    "`bottom_id`)" +
                    " VALUES ('?', '?', '?', '?');";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
            /*    ps.setInt(1, );
                ps.setDouble(2, weight );
                ps.setString(3, category);
                ps.setDouble(4, bmi);
                ps.setString(5, gender);
                ps.setInt(6,sport_id);
                ps.setInt(7,user_id);

                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int bmiEntryId = ids.getInt(1);
                //TODO: Her skal vi indsætte hobbyer i link_bmi_hobby tabellen

                for (Integer hobbyId : hobbyList) {
                    insertIntoLinkHobbyBmiEntry(bmiEntryId,hobbyId);
                }*/
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
