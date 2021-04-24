package web.commands;

import business.entities.Orderline;
import business.exceptions.UserException;
import business.services.OrderlineFacade;
import business.services.OrdersFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CommandOrderLines extends CommandProtectedPage{
    OrderlineFacade orderlineFacade;
    OrdersFacade ordersFacade;

    public CommandOrderLines(String pageToShow, String role) {
        super(pageToShow, role);
        orderlineFacade = new OrderlineFacade(database);
        ordersFacade = new OrdersFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();

        String orderid = request.getParameter("orderid");
        String orderIdToDelete = request.getParameter("orderidtodelete");
        if (orderid != null) {
            int orderId = Integer.parseInt(request.getParameter("orderid"));
            System.out.println("orderId" + orderId);
            List<Orderline> orderlineList = orderlineFacade.getAllOrderlinesById(orderId);
            session.setAttribute("orderlineList", orderlineList);
        }
        if (orderIdToDelete != null) {
            int orderIdToDelete1 = Integer.parseInt(request.getParameter("orderidtodelete"));
            int test = orderlineFacade.deleteOrderLinesByOrderId(orderIdToDelete1);
            System.out.println(test);
            ordersFacade.deleteOrder(orderIdToDelete1);
        }


        return pageToShow;
    }
}
