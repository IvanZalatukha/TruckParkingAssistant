package by.zalatukha.tpa.command.impl;

import by.zalatukha.tpa.command.Command;
import by.zalatukha.tpa.entity.User;
import by.zalatukha.tpa.command.JspPath;
import by.zalatukha.tpa.command.ResponseContext;
import by.zalatukha.tpa.command.ResponseType;
import by.zalatukha.tpa.dao.impl.ImplParkingCRUD;
import by.zalatukha.tpa.dao.impl.ImplParkingsServicesCRUD;
import by.zalatukha.tpa.entity.Parking;
import by.zalatukha.tpa.entity.ServicesProvidedByParking;
import by.zalatukha.tpa.service.GetAllParkingsFromDB;
import by.zalatukha.tpa.service.SetRandomNumberOfCurrentSpots;
import by.zalatukha.tpa.service.UserService;
import by.zalatukha.tpa.util.LoggerUtil;

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
                List<Parking> allParkings = GetAllParkingsFromDB.getAllParkingsFromDB();
                List<ServicesProvidedByParking> allServices = GetAllParkingsFromDB.getAllServicesFromDB();

                if (currentUser.getLogin().equals("admin")) {
                    httpSession.setAttribute("isAdmin", true);
                    LoggerUtil.getInstance().info("Administrator logged in");
                    return new ResponseContext(JspPath.MAIN_PAGE.getPath(), ResponseType.REDIRECT);
                }

                httpSession.setAttribute("allServices", allServices);
                httpSession.setAttribute("allParkings", allParkings);
                httpSession.setAttribute("isUser", true);
                httpSession.setAttribute("user", currentUser);
                LoggerUtil.getInstance().info("User " + currentUser + "logged in");
                return new ResponseContext(JspPath.MAP_PAGE.getPath(), ResponseType.REDIRECT);
            }
        }

        httpSession.setAttribute("wrongInput", true);
        LoggerUtil.getInstance().info("Sign in. Incorrect login or password");
        return new ResponseContext(JspPath.SIGN_IN_PAGE.getPath(), ResponseType.REDIRECT);
    }
}
