package web.commands;

import business.entities.Orders;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrdersFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

public class CommandAddToBasket extends CommandProtectedPage {

    private OrdersFacade ordersFacade;

    private UserFacade userFacade;
    public CommandAddToBasket(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
        ordersFacade = new OrdersFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        HttpSession session = request.getSession();
        User user = null;
        int user_id = 1;

        if (session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
            user_id = user.getId();
        } else {
//Hvis user ikke er forskellig fra null, skal man ikke kan ligge ting i sin kurv
        }

        int userId = user.getId();
        Timestamp created = null;
        String status = "";
        List<Orders> ordersList = ordersFacade.getAllOrders();
        for (Orders orders : ordersList) {
            if(!orders.getStatus().equals("In basket") && orders.getUserId() == userId){
                ordersFacade.insertOrder(userId,created,status);
            }
        }

        status = "In progress";






        return pageToShow;
    }
}

