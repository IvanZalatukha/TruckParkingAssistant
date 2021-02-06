package by.zalatukha.tpa.command.impl;

import by.zalatukha.tpa.command.Command;
import by.zalatukha.tpa.command.JspPath;
import by.zalatukha.tpa.command.ResponseContext;
import by.zalatukha.tpa.command.ResponseType;
import by.zalatukha.tpa.dao.impl.ImplUserCRUD;
import by.zalatukha.tpa.entity.Role;
import by.zalatukha.tpa.entity.User;
import by.zalatukha.tpa.service.RegistrationUserService;
import by.zalatukha.tpa.util.LoggerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationUser implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!RegistrationUserService.checkForCorrectInput(firstName, lastName, email, phoneNumber,
                login, password, confirmPassword, request)) {
            return new ResponseContext(JspPath.REGISTRATION_PAGE.getPath(), ResponseType.REDIRECT);
        }
        User user = new User(login, password, firstName, lastName, email, Role.USER);
        user.setId(ImplUserCRUD.getInstance().findAll().size() + 1);
        user.setPhoneNumber(Long.parseLong(phoneNumber));
        ImplUserCRUD.getInstance().create(user);

        LoggerUtil.getInstance().info("User "+ firstName + " " + lastName + " registered in the system");
        return new ResponseContext(JspPath.SIGN_IN_PAGE.getPath(), ResponseType.REDIRECT);

    }
}
