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

public class ManageOrderlineCommand extends CommandProtectedPage{
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

        if (session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
            userId = user.getId();
        } else {
//Hvis user ikke er forskellig fra null, skal man ikke kan ligge ting i sin kurv
        }


Orderline orderline = null;
//TODO: Delete skal laves og totalprice skal opdateres.
        String delete = request.getParameter("delete");
        String pay = request.getParameter("pay");
        String edit = request.getParameter("edit");


        //TODO: Der skal checkes om balanace er høj nok
        if (pay != null) {
            //tjekker om balance er høj nok
            user = userFacade.getUserById(userId);
            double totalPrice = (double) session.getAttribute("totalprice");
            if (totalPrice != 0){
            double userBalance = user.getBalance();
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
                //tømmer kurven efter kunden har betalt
                orderlineList.clear();
                session.setAttribute("orderlineList", orderlineList);
                //sætter den totale pris til 0 efter kurven er tømt.
                session.setAttribute("totalprice", 0);
                double newBalance = user.getBalance() - totalPrice;
                user.setBalance(newBalance);
                userFacade.insertBalance(user);
            }


            } else {
                request.setAttribute("error","You do not have enough money :(. Or basket empty?");
            }

            //TODO: Returnér brugeren til en kvitteringside.



        }
        if (delete != null) {
            List<Orderline> orderlineList = (List<Orderline>) session.getAttribute("orderlineList");

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

            //Udregn samlede pris for orderline via et orderId
            double totalPrice = 0;
            for (Orderline orderline1 : orderlineList) {
                totalPrice += orderline1.getPrice();


                //orderline = request.getParameter("delete");
                session.setAttribute("orderlineList", orderlineList);


            }
            session.setAttribute("totalprice", totalPrice);
        }

        //session.setAttribute("");



        return pageToShow;
    }
}
