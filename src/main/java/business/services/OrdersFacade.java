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

    public int insertOrder(int userId, Timestamp timestamp, String status) throws UserException {
        return orderMapper.insertOrder(userId, timestamp, status);
    }

    public List<Orders> getAllOrders() throws UserException {
        return orderMapper.getAllOrders();
    }

    public List<Orders> getOrdersByUserId(int userId) throws UserException {
        return orderMapper.getOrdersByUserId(userId);
    }
}
