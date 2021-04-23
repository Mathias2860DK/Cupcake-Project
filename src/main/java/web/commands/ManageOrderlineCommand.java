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
import java.util.concurrent.CopyOnWriteArrayList;

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

        HttpSession session = request.getSession();



Orderline orderline = null;
//TODO: Delete skal laves og totalprice skal opdateres.
        String deleteOrderline = request.getParameter("delete");
        String editOrderline = request.getParameter("edit"); //TODO: Make it work
        if (deleteOrderline != null) {
            List<Orderline> orderlineList = (List<Orderline>) session.getAttribute("orderlineList");
//deleteordliner svarer ikke til indexet. Check om deleteordline (id) findes i orderlinelisten. hvis ja så slet den orderline
            if (orderlineList != null) {

                for (Orderline orderline1 : orderlineList) { //denne linje giver en ConcurrentModificationException. Men metoden virker.. TODO MAKE IT WORK
                    int cartItem = orderline1.getCartItem();
                    int orderlineItem = Integer.parseInt(deleteOrderline);
                    if (cartItem == orderlineItem) {
                        orderlineList.remove(orderline1);
                        session.setAttribute("orderlineList", orderlineList);
                        System.out.println("kommer vi ind her for loop");

                    }
                }
            }

            //Udregn samlede pris for orderline via et orderId
            double totalPrice = 0;
            for (Orderline orderline1 : orderlineList) {
                totalPrice += orderline1.getPrice();
                session.setAttribute("totalprice", totalPrice);


                System.out.println(deleteOrderline);
                //orderline = request.getParameter("delete");
                session.setAttribute("orderlineList", orderlineList);


            }
        }
        //session.setAttribute("");



        return pageToShow;
    }
}
