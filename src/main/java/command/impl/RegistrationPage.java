package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;
import dao.impl.ImplUserCRUD;
import domain.Role;
import domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationPage implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {
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
        String email = request.getParameter("email");
        User user = new User(login, password, firstName,lastName, email, Role.USER);
        user.setId(ImplUserCRUD.getInstance().numberOfUsers() + 1);
        if(phoneNumber != null) {
            user.setPhoneNumber(Long.parseLong(phoneNumber));
        }
        if (firstName != null) {
            ImplUserCRUD.getInstance().create(user);
            return new ResponseContext(JspPath.SIGN_IN_PAGE.getPath(), ResponseType.FORWARD);
        }
        return new ResponseContext(JspPath.REGISTRATION_PAGE.getPath(), ResponseType.FORWARD);

    }
}
