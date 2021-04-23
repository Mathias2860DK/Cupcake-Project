package web.commands;

import business.entities.*;
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
        Orderline orderline = null;
        List<Orderline> orderlineList = null;
        int orderId = 0;
        List<Bottom> bottomList = (List<Bottom>) session.getServletContext().getAttribute("bottomList");
        List<Topping> toppingList = (List<Topping>) session.getServletContext().getAttribute("toppingList");
        int bottomId = Integer.parseInt(request.getParameter("bottom"));
        int toppingId = Integer.parseInt(request.getParameter("topping"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = CalcPrice.calcOrderLinePrice(bottomId, toppingId, quantity, bottomList, toppingList);
        int user_id = 0; //hed "1" før
        orderline = new Orderline(quantity,price,toppingId,bottomId);
        //Se om listen a orderlines eksistere på sessionscope
        orderlineList = (List<Orderline>) session.getAttribute("orderlineList");
        if (orderlineList == null){
            orderlineList = new ArrayList<>();
        }
        orderlineList.add(orderline);

        session.setAttribute("orderlineList",orderlineList);


            return pageToShow;
        }
    }



