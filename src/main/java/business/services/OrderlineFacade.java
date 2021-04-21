package business.services;

import business.entities.Orderline;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderlineMapper;

import java.sql.Timestamp;

public class OrderlineFacade {
    OrderlineMapper orderlineMapper;

    public OrderlineFacade(Database database){
        orderlineMapper = new OrderlineMapper(database);
    }
    public Orderline insertOrderline(int orderId,
                                     int quantity,
                                     double price,
                                     int toppingId,
                                     int bottomId) throws UserException {
         return orderlineMapper.insertOrderline(orderId, quantity, price, toppingId, bottomId);
    }
}
