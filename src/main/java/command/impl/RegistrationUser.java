package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;
import dao.impl.ImplUserCRUD;
import entity.Role;
import entity.User;
import validation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationUser implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");



        if (firstName == null || lastName == null || email == null || login == null ||
                password == null || confirmPassword == null) {
            System.out.println("11111");
            httpSession.setAttribute("wrongInput", true);
            return new ResponseContext(JspPath.REGISTRATION_PAGE.getPath(), ResponseType.REDIRECT);
        }

        NameValidation nameValidation = new NameValidation();
        if (!nameValidation.validate(firstName)) {
            httpSession.setAttribute("wrongFirstNameInput", true);
            return new ResponseContext(JspPath.REGISTRATION_PAGE.getPath(), ResponseType.REDIRECT);
        }
        if (!nameValidation.validate(lastName)) {
            httpSession.setAttribute("wrongLastNameInput", true);
            return new ResponseContext(JspPath.REGISTRATION_PAGE.getPath(), ResponseType.REDIRECT);
        }

        EmailValidation emailValidation = new EmailValidation();
        if (!emailValidation.validate(email)) {
            httpSession.setAttribute("wrongEmailInput", true);
            return new ResponseContext(JspPath.REGISTRATION_PAGE.getPath(), ResponseType.REDIRECT);
        }

        PhoneNumberValidation phoneNumberValidation = new PhoneNumberValidation();
        if (!phoneNumberValidation.validate(phoneNumber)) {
            httpSession.setAttribute("wrongPhoneNumberInput", true);
            return new ResponseContext(JspPath.REGISTRATION_PAGE.getPath(), ResponseType.REDIRECT);
        }

        LoginValidation loginValidation = new LoginValidation();
        if (!loginValidation.validate(login)) {
            httpSession.setAttribute("wrongLoginInput", true);
            return new ResponseContext(JspPath.REGISTRATION_PAGE.getPath(), ResponseType.REDIRECT);
        }
        if(ImplUserCRUD.getInstance().findByLogin(login) != null) {
            httpSession.setAttribute("loginAlreadyHas", true);
            return new ResponseContext(JspPath.REGISTRATION_PAGE.getPath(), ResponseType.REDIRECT);
        }

        PasswordValidation passwordValidation = new PasswordValidation();
        if (!password.equals(confirmPassword)) {
            httpSession.setAttribute("passwordAndConfirmNotEqualsInput", true);
            return new ResponseContext(JspPath.REGISTRATION_PAGE.getPath(), ResponseType.REDIRECT);
        }
        if (!passwordValidation.validate(password)) {
            httpSession.setAttribute("wrongPasswordInput", true);
            return new ResponseContext(JspPath.REGISTRATION_PAGE.getPath(), ResponseType.REDIRECT);
        }

        User user = new User(login, password, firstName, lastName, email, Role.USER);
        user.setId(ImplUserCRUD.getInstance().numberOfUsers() + 1);
        user.setPhoneNumber(Long.parseLong(phoneNumber));
        ImplUserCRUD.getInstance().create(user);
        return new ResponseContext(JspPath.SIGN_IN_PAGE.getPath(), ResponseType.REDIRECT);

    }
}
