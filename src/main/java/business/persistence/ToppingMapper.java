package business.persistence;

import business.entities.Bottom;
import business.entities.Topping;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToppingMapper {
    private Database database;

    public ToppingMapper(Database database) {
        this.database = database;
    }

    public List<Topping> getAllToppings() throws UserException {
        List<Topping> toppingList = new ArrayList<>();
        try (Connection connection = database.connect())
        {
            String sql = "SELECT * FROM olskerCupcake.topping;";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int toppingId = rs.getInt("topping_id");
                    String tName = rs.getString("tname");
                    double price = rs.getDouble("price");

                    Topping topping = new Topping(toppingId,tName,price);
                    toppingList.add(topping);

                }
                return toppingList;
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
