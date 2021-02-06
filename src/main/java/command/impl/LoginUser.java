package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;
import dao.impl.ImplParkingCRUD;
import dao.impl.ImplParkingsServicesCRUD;
import entity.Parking;
import entity.ServicesProvidedByParking;
import entity.User;
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
        HttpSession httpSession = request.getSession();
        if (!login.isEmpty() && !password.isEmpty()) {
            User currentUser = new User();
            currentUser.setLogin(login);
            currentUser.setPassword(password);
            if (UserService.login(currentUser)) {
                List<Parking> allParkings = SetRandomNumberOfCurrentSpots.setCurrentSpots(
                        ImplParkingCRUD.getInstance().findAll());
                List<ServicesProvidedByParking> allServices = ImplParkingsServicesCRUD.getInstance().findAll();
                for (int i = 0; i < allParkings.size(); i++) {
                    allParkings.get(i).setParkingServices(allServices.get(i));
                }
                if (currentUser.getLogin().equals("admin")) {
                    httpSession.setAttribute("isAdmin", true);
                    return new ResponseContext(JspPath.MAIN_PAGE.getPath(), ResponseType.REDIRECT);
                }


                httpSession.setAttribute("allServices", allServices);
                httpSession.setAttribute("allParkings", allParkings);
                httpSession.setAttribute("isUser", true);
                httpSession.setAttribute("user", currentUser);

                return new ResponseContext(JspPath.MAP_PAGE.getPath(), ResponseType.REDIRECT);
            }
        }

        httpSession.setAttribute("wrongInput", true);
        return new ResponseContext(JspPath.SIGN_IN_PAGE.getPath(), ResponseType.REDIRECT);
    }
}
