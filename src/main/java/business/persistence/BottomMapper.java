package business.persistence;

import business.entities.Bottom;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BottomMapper {
    private Database database;

    public BottomMapper(Database database) {
        this.database = database;
    }

    public List<Bottom> getAllBottoms() throws UserException {
        List<Bottom> bottomList = new ArrayList<>();
        try (Connection connection = database.connect())
        {
            String sql = "SELECT * FROM olskerCupcake.bottom;";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int bottomId = rs.getInt("bottom_id");
                    String bName = rs.getString("bname");
                    double price = rs.getDouble("price");

                    Bottom bottom = new Bottom(bottomId,bName,price);
                    bottomList.add(bottom);

                }
                return bottomList;
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException | UserException ex)
        {
            throw new UserException("Connection to database could not be established");
        }
    }
}
