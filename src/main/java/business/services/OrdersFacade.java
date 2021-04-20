package business.services;

import business.entities.Orders;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;

import java.sql.Timestamp;
import java.util.List;

public class OrdersFacade {
    OrderMapper orderMapper;

    public OrdersFacade(Database database) {
        orderMapper = new OrderMapper(database);
    }

    public void insertOrder(int userId, Timestamp timestamp, String status) throws UserException {
        orderMapper.insertOrder(userId, timestamp, status);
    }

    public List<Orders> getAllOrders() throws UserException {
        return orderMapper.getAllOrders();
    }
}
