package business.services;

import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;

import java.sql.Timestamp;

public class OrdersFacade {
    OrderMapper orderMapper;

    public OrdersFacade(Database database) {
        orderMapper = new OrderMapper(database);
    }

    public void insertOrder(int userId, Timestamp timestamp, String status) throws UserException {
        orderMapper.insertOrder(userId,timestamp,status);
    }
}
