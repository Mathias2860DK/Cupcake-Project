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

public class ShowBasketCommand extends CommandProtectedPage {

    private OrdersFacade ordersFacade;
    private OrderlineFacade orderlineFacade;
    public ShowBasketCommand(String pageToShow, String role) {
        super(pageToShow, role);
        ordersFacade = new OrdersFacade(database);
        orderlineFacade = new OrderlineFacade(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        User user = null;
        int userId = 0;
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
            userId = user.getId();
        } else {
//Hvis user ikke er forskellig fra null, skal man ikke kan ligge ting i sin kurv
        }
        //find ordre_id via user_id
        int orderID = ordersFacade.getOrderIdByUserIdAndStatus(userId);

        //Find alle ordelines via orderID
List<Orderline> orderlineList = orderlineFacade.getAllOrderlinesById(orderID);

//Udregn samlede pris for orderline via et orderId
        double totalPrice = 0;
        for (Orderline orderline : orderlineList) {
            totalPrice +=orderline.getPrice();
        }

        session.setAttribute("totalprice",totalPrice);

        session.setAttribute("orderlineList",orderlineList);




        return pageToShow;
    }
}
