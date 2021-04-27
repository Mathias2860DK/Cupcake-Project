package web.commands;

import business.entities.Orderline;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderlineFacade;
import business.services.OrdersFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ManageOrderlineCommand extends CommandProtectedPage {
    private OrderlineFacade orderlineFacade;
    private OrdersFacade ordersFacade;
    private UserFacade userFacade;

    public ManageOrderlineCommand(String pageToShow, String role) {
        super(pageToShow, role);
        orderlineFacade = new OrderlineFacade(database);
        ordersFacade = new OrdersFacade(database);
        userFacade = new UserFacade(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        HttpSession session = request.getSession();
        User user = null;
        int userId = 0;

        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
            userId = user.getId();
        } else {
            //Client should not be able to access this site without being logged in
            request.setAttribute("error", "You should be logged in before putting items in basket");
        }

        //Gets the parameter data
        String delete = request.getParameter("delete");
        String pay = request.getParameter("pay");
        String edit = request.getParameter("edit");
        //Checks if pay parameter is initialized
        if (pay != null) {
            user = userFacade.getUserById(userId);
            double totalPrice = (double) session.getAttribute("totalprice");
            if (totalPrice != 0) {
                double userBalance = user.getBalance();
                //Checks if the users balance is more than the total price of the orderlines.
                if (userBalance > totalPrice) {
                    List<Orderline> orderlineList = (List<Orderline>) session.getAttribute("orderlineList");
                    int orderId = ordersFacade.insertOrder(userId, new Timestamp(System.currentTimeMillis()), "paid");
                    for (Orderline orderline1 : orderlineList) {
                        int quantity = orderline1.getQuantity();
                        double price = orderline1.getPrice();
                        int toppingId = orderline1.getToppingId();
                        int bottomId = orderline1.getBottomId();
                        orderlineFacade.insertOrderline(orderId, quantity, price, toppingId + 1, bottomId + 1);//lappe løsning
                    }
                    //Empty basket after customer pays
                    orderlineList.clear();
                    session.setAttribute("orderlineList", orderlineList);
                    //Sets totalprice attribute to 0 after the customer has paid
                    session.setAttribute("totalprice", 0);

                    //Updates the users balance
                    double newBalance = user.getBalance() - totalPrice;
                    user.setBalance(newBalance);
                    userFacade.insertBalance(user);
                }

            } else {
                request.setAttribute("error", "You do not have enough money :(. Or basket empty?");
            }

            //TODO: Returnér brugeren til en kvitteringside.

        }
        //Checks if delete parameter is initialized
        if (delete != null) {
            List<Orderline> orderlineList = (List<Orderline>) session.getAttribute("orderlineList");
            //Deletes the selected orderline.
            if (orderlineList != null) {
                for (int i = 0; i < orderlineList.size(); i++) {
                    int cartItem = orderlineList.get(i).getCartItem();
                    int orderlineItem = Integer.parseInt(delete);
                    if (cartItem == orderlineItem) {
                        orderlineList.remove(orderlineList.remove(i));
                        i--;
                    }
                }
            }

            //Updates the price of the new cart
            double totalPrice = 0;
            for (Orderline orderline1 : orderlineList) {
                totalPrice += orderline1.getPrice();
                session.setAttribute("orderlineList", orderlineList);
            }
            session.setAttribute("totalprice", totalPrice);
        }

        return pageToShow;
    }
}
