package command.impl;

import command.Command;
import command.JspPath;
import dao.impl.ImplUserCRUD;
import domain.Role;
import domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        if (request.getParameter("password").equals(request.getParameter("confirmPassword"))) {
//
//        } else {
//
//        }
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login, password, firstName,lastName, Role.USER);
        user.setId(ImplUserCRUD.getInstance().numberOfUsers() + 1);
        if(phoneNumber != null) {
            user.setPhoneNumber(Long.parseLong(phoneNumber));
        }
        if (firstName != null) {
            ImplUserCRUD.getInstance().create(user);
            return JspPath.SIGN_IN_PAGE.getPath();
        }
        else return JspPath.REGISTRATION_PAGE.getPath();
    }
}
