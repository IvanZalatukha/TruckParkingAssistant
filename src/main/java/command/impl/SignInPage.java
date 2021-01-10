package command.impl;

import command.Command;
import command.JspPath;
import dao.impl.ImplParkingCRUD;
import domain.Parking;
import domain.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SignInPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("METHOD SIGN IN PAGE");
        String login = request.getParameter("login");

        System.out.println(login);
        String password = request.getParameter("password");
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        if (UserService.login(user)) {
            List allParkings = ImplParkingCRUD.getInstance().findAll();
            request.setAttribute("parkings", allParkings);
            return JspPath.MAP_PAGE.getPath();
        }

        return JspPath.SIGN_IN_PAGE.getPath();
    }
}
