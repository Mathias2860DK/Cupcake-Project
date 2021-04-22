package web.commands;

import business.entities.Orderline;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderlineFacade;
import business.services.OrdersFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ManageOrderlineCommand extends CommandProtectedPage{
    private OrderlineFacade orderlineFacade;
    private OrdersFacade ordersFacade;

    public ManageOrderlineCommand(String pageToShow, String role) {
        super(pageToShow, role);
        orderlineFacade = new OrderlineFacade(database);
        ordersFacade = new OrdersFacade(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        System.out.println(" does it wooork");
        int userId = 0;
        User user = null;
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
            userId = user.getId();
        } else {
//Hvis user ikke er forskellig fra null, skal man ikke kan ligge ting i sin kurv
        }
        //find ordre_id via user_id
        int orderID = ordersFacade.getOrderIdByUserIdAndStatus(userId);


        String deleteOrderline = request.getParameter("delete"); //value = orderlineID
        String editOrderline = request.getParameter("edit"); //TODO: Make it work

        if (deleteOrderline != null){
            //delete from database.
            int rowsAffected = orderlineFacade.deleteOrderline(Integer.parseInt(deleteOrderline));
            if (rowsAffected > 0){

                orderlineFacade.getAllOrderlinesById(orderID);
                List<Orderline> orderlineList = orderlineFacade.getAllOrderlinesById(orderID);
                double totalPrice = 0;
                for (Orderline orderline : orderlineList) {
                    totalPrice +=orderline.getPrice();
                }

                session.setAttribute("totalprice",totalPrice);

                session.setAttribute("orderlineList",orderlineList);
            }

        }

        //Find alle ordelines via orderID


//Udregn samlede pris for orderline via et orderId


        return pageToShow;
    }
}
