package web.commands;

import business.entities.Orders;
import business.exceptions.UserException;
import business.persistence.OrderlineMapper;
import business.services.OrderlineFacade;
import business.services.OrdersFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrdersAndOrderlines extends CommandProtectedPage {
    OrdersFacade ordersFacade;
    OrderlineFacade orderlineFacade;

    public OrdersAndOrderlines(String pageToShow, String role) {
        super(pageToShow, role);
       ordersFacade = new OrdersFacade(database);
       orderlineFacade = new OrderlineFacade(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        String editBalance = request.getParameter("editbalance");
        String userId = request.getParameter("userid");

        if (userId != null){

            int userIdInt = Integer.parseInt(request.getParameter("userid"));
            List<Orders> ordersList = ordersFacade.getOrdersByUserId(userIdInt);
            session.setAttribute("orderList",ordersList);
        }

        if (editBalance != null){
            System.out.println("hey");
            return
        }








        return pageToShow;
    }
}
