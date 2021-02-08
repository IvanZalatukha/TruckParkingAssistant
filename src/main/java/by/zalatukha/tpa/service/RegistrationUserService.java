package by.zalatukha.tpa.service;

import by.zalatukha.tpa.dao.impl.ImplUserCRUD;
import by.zalatukha.tpa.validation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * Designed to handle invalid input parameters coming from the registration page
 */
public class RegistrationUserService {
    public static boolean checkForCorrectInput(String firstName, String lastName, String email, String phoneNumber,
                                               String login, String password, String confirmPassword, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();

        if (firstName == null || lastName == null || email == null || login == null ||
                password == null || confirmPassword == null) {
            httpSession.setAttribute("wrongInput", true);
            return false;
        }

        NameValidation nameValidation = new NameValidation();
        if (!nameValidation.validate(firstName)) {
            httpSession.setAttribute("wrongFirstNameInput", true);
            return false;
        }
        if (!nameValidation.validate(lastName)) {
            httpSession.setAttribute("wrongLastNameInput", true);
            return false;
        }

        EmailValidation emailValidation = new EmailValidation();
        if (!emailValidation.validate(email)) {
            httpSession.setAttribute("wrongEmailInput", true);
            return false;
        }

        PhoneNumberValidation phoneNumberValidation = new PhoneNumberValidation();
        if (!phoneNumberValidation.validate(phoneNumber)) {
            httpSession.setAttribute("wrongPhoneNumberInput", true);
            return false;
        }

        LoginValidation loginValidation = new LoginValidation();
        if (!loginValidation.validate(login)) {
            httpSession.setAttribute("wrongLoginInput", true);
            return false;

        }
        if (ImplUserCRUD.getInstance().findByLogin(login) != null) {
            httpSession.setAttribute("loginAlreadyHas", true);
            return false;
        }

        PasswordValidation passwordValidation = new PasswordValidation();
        if (!password.equals(confirmPassword)) {
            httpSession.setAttribute("passwordAndConfirmNotEqualsInput", true);
            return false;
        }

        if (!passwordValidation.validate(password)) {
            httpSession.setAttribute("wrongPasswordInput", true);
            return false;
        }
        return true;
    }
}
