package web.commands;

import business.entities.Bottom;
import business.entities.Orders;
import business.entities.Topping;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderlineFacade;
import business.services.OrdersFacade;
import business.services.UserFacade;
import business.util.CalcPrice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommandAddToBasket extends CommandProtectedPage {

    private OrdersFacade ordersFacade;
    private OrderlineFacade orderlineFacade;

    private UserFacade userFacade;
    public CommandAddToBasket(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
        ordersFacade = new OrdersFacade(database);
        orderlineFacade = new OrderlineFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        HttpSession session = request.getSession();
        User user = null;
        int orderId = 10;
        List<Bottom> bottomList = (List<Bottom>) session.getServletContext().getAttribute("bottomList");
        List<Topping> toppingList = (List<Topping>) session.getServletContext().getAttribute("toppingList");
        int bottomId = Integer.parseInt(request.getParameter("bottom"));
        int toppingId = Integer.parseInt(request.getParameter("topping"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = CalcPrice.calcOrderLinePrice(bottomId, toppingId, quantity, bottomList, toppingList);
        int user_id = 0; //hed "1" før


        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
            user_id = user.getId();
        } else {
//Hvis user ikke er forskellig fra null, skal man ikke kan ligge ting i sin kurv
        }

        int userId = user.getId();
        Timestamp created = null;
        String status = "In basket";
        List<Orders> ordersList = ordersFacade.getAllOrders(); //evt by userId
        List<Orders> ordersListByUserId = ordersFacade.getOdersByUserId(userId);
        //Hvis der er et userId med en ordre status "In basket" skal der ikke
        //oprettes en ordre.
        //Hvis der ikke er et specifikt userId med en en status "In basket" skal
        //der oprettes en ondre på det userId. med status "In basket"
        if (ordersListByUserId.isEmpty()) {
            ordersList = new ArrayList<>();
            orderId = ordersFacade.insertOrder(userId, created, status);
        }
       int tempOrderId = 0;

        for (Orders orders : ordersListByUserId) {
            if (orders.getUserId() == userId && orders.getStatus().equals("In basket")) {
                orderlineFacade.insertOrderline(orderId, quantity, price, toppingId, bottomId);
            } else {
                tempOrderId = ordersListByUserId.indexOf(orders.getOrderId());
            }

        }
        if (tempOrderId != 0 ){
            System.out.println(tempOrderId);
            ordersFacade.insertOrder(userId,created,status);
        }
        /*
        for (Orders orders : ordersList) {
            if (orders.getUserId() == userId && orders.getStatus().equals("In basket")){
               orderlineFacade.insertOrderline(orders.getOrderId(),quantity, price,toppingId, bottomId );
            } else if (orders.getOrderId()){
                ordersFacade.insertOrder(userId,created,status);
                orderlineFacade.insertOrderline(orderId,quantity, price,toppingId, bottomId );
                }
            }*/

            return pageToShow;
        }
    }



