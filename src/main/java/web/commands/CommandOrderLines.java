package web.commands;

import business.entities.Orderline;
import business.exceptions.UserException;
import business.services.OrderlineFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CommandOrderLines extends CommandProtectedPage{
    OrderlineFacade orderlineFacade;

    public CommandOrderLines(String pageToShow, String role) {
        super(pageToShow, role);
        orderlineFacade = new OrderlineFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderid"));
        List<Orderline> orderlineList = orderlineFacade.getAllOrderlinesById(orderId);
        session.setAttribute("orderlineList",orderlineList);

        return pageToShow;
    }
}
