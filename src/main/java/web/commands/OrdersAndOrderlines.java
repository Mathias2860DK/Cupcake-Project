package web.commands;

import business.entities.Orders;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.OrderlineMapper;
import business.services.OrderlineFacade;
import business.services.OrdersFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrdersAndOrderlines extends CommandProtectedPage {
    OrdersFacade ordersFacade;
    OrderlineFacade orderlineFacade;
    UserFacade userFacade;

    public OrdersAndOrderlines(String pageToShow, String role) {
        super(pageToShow, role);
       ordersFacade = new OrdersFacade(database);
       orderlineFacade = new OrderlineFacade(database);
       userFacade = new UserFacade(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        String editBalance = request.getParameter("editbalance");
        String userId = request.getParameter("userid");
        String userIdValue = request.getParameter("confirm");

        if (userId != null){

            int userIdInt = Integer.parseInt(userId);
            List<Orders> ordersList = ordersFacade.getOrdersByUserId(userIdInt);
            session.setAttribute("orderList",ordersList);
        }

        if (userIdValue != null){
            //skal tilføjes
            //try catch skal laves

            int editBalanceInt = Integer.parseInt(editBalance);
            int userIdValueInt = Integer.parseInt(userIdValue);
            System.out.println(editBalanceInt);
            session.getAttribute("userList");
            User user = userFacade.getUserById(userIdValueInt);
            //den nuværende balance
            double currentBalance = user.getBalance();
            double newBalance = currentBalance + editBalanceInt;
            user.setBalance(newBalance);
            userFacade.insertBalance(user);
            List<User> userList = userFacade.getAllUsers();
            session.setAttribute("userList",userList);
            request.setAttribute("sucess","Succes! Monney has been inserted");

return "test";
        }








        return pageToShow;
    }
}
