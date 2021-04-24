package business.services;

import business.entities.Orderline;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderlineMapper;

import java.sql.Timestamp;
import java.util.List;

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
    public List<Orderline> getAllOrderlinesById(int orderId) throws UserException {
        return orderlineMapper.getAllOrderlinesById(orderId);
    }
    public int deleteOrderline(int orderlineId) throws UserException {
        return orderlineMapper.deleteOrderline(orderlineId);
    }
    public int deleteOrderLinesByOrderId(int orderId) throws UserException {
        return orderlineMapper.deleteOrderLinesByOrderId(orderId);
    }
}
