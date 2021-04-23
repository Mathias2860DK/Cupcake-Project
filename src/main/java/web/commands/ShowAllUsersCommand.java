package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowAllUsersCommand extends CommandProtectedPage{
    UserFacade userFacade;
    public ShowAllUsersCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();

        List<User> userList = userFacade.getAllUsers();
        System.out.println("kommer vi ind her ");
        System.out.println(this.role);
        session.setAttribute("userList",userList);
        request.setAttribute("userList",userList);


        return pageToShow;
    }
}
