package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;
import dao.impl.ImplParkingCRUD;
import dao.impl.ImplParkingsServicesCRUD;
import domain.Parking;
import domain.ServicesProvidedByParking;
import domain.User;
import service.SetRandomNumberOfCurrentSpots;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginUser implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login != null && password != null){
            User currentUser = new User();
            currentUser.setLogin(login);
            currentUser.setPassword(password);
            if (UserService.login(currentUser)) {

                List<Parking> allParkings = SetRandomNumberOfCurrentSpots.setCurrentSpots(
                        ImplParkingCRUD.getInstance().findAll());
                List<ServicesProvidedByParking> allServices = ImplParkingsServicesCRUD.getInstance().findAll();

                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("allServices", allServices);
                httpSession.setAttribute("allParkings", allParkings);

                return new ResponseContext(JspPath.MAP_PAGE.getPath(), ResponseType.FORWARD);
            }
        }
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("wrongInput", true);
        return new ResponseContext(JspPath.SIGN_IN_PAGE.getPath(), ResponseType.FORWARD);
    }
}
