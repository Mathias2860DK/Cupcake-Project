package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

public class CommandAddToBasket extends CommandProtectedPage {

    private UserFacade userFacade;
    public CommandAddToBasket(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        User user = null;
        int user_id = 1;
        if (session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
            user_id = user.getId();
        } else {
//Hvis user ikke er forskellig fra null, skal man ikke kan ligge ting i sin kurv
        }
        int orderId = 0;
        int userId = user.getId();
        Timestamp created = null;
        String status = "";



        return super.execute(request, response);
    }
}

