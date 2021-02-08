package by.zalatukha.tpa.command.impl;

import by.zalatukha.tpa.command.Command;
import by.zalatukha.tpa.command.ResponseContext;
import by.zalatukha.tpa.command.ResponseType;
import by.zalatukha.tpa.dao.impl.ImplUserCRUD;
import by.zalatukha.tpa.entity.User;
import by.zalatukha.tpa.command.JspPath;
import by.zalatukha.tpa.service.SendEmailService;
import by.zalatukha.tpa.util.LoggerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ForgotPassword implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        HttpSession httpSession = request.getSession();
        if (!login.isEmpty() && !email.isEmpty()) {
            User userFromDB;
            try {
                userFromDB = ImplUserCRUD.getInstance().findByLogin(login);
                userFromDB.setEmail(ImplUserCRUD.getInstance().findByLogin(login).getEmail());
            } catch (Exception e) {
                httpSession.setAttribute("InvalidInput", true);
                LoggerUtil.getInstance().error("User with such a login and email does not exist");
                return new ResponseContext(JspPath.FORGOT_PASSWORD.getPath(), ResponseType.REDIRECT);
            }
            try {
                if (userFromDB.getEmail().equals(email)) {
                    String passwordForSending = ImplUserCRUD.getInstance().findByLogin(login).getPassword();
                    userFromDB.setPassword(passwordForSending);
                    SendEmailService sendEmailService = new SendEmailService();
                    sendEmailService.sendEmailToUSer(userFromDB);
                    httpSession.setAttribute("validInput", true);
                    return new ResponseContext(JspPath.FORGOT_PASSWORD.getPath(), ResponseType.REDIRECT);
                }
            } catch (Exception e) {
                e.printStackTrace();
                httpSession.setAttribute("InvalidInput", true);
                LoggerUtil.getInstance().error("Forgot Password. Incorrect login or email");
                return new ResponseContext(JspPath.FORGOT_PASSWORD.getPath(), ResponseType.REDIRECT);
            }
        }
        httpSession.setAttribute("wrongInput", true);
        return new ResponseContext(JspPath.FORGOT_PASSWORD.getPath(), ResponseType.REDIRECT);
    }
}
