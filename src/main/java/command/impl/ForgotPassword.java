package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;
import dao.impl.ImplUserCRUD;
import entity.User;
import service.SendEmail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ForgotPassword implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        HttpSession httpSession = request.getSession();
        System.out.println(1);
        if (!login.isEmpty() && !email.isEmpty()) {
            User userFromDB;
            try {
                System.out.println(2);
                userFromDB = ImplUserCRUD.getInstance().findByLogin(login);
                userFromDB.setEmail(ImplUserCRUD.getInstance().findByLogin(login).getEmail());
            } catch (Exception e) {
                System.out.println(3);
                httpSession.setAttribute("InvalidInput", true);
                return new ResponseContext(JspPath.FORGOT_PASSWORD.getPath(), ResponseType.FORWARD);
            }
            try {
                System.out.println(4);
                if (userFromDB.getEmail().equals(email)) {
                    String passwordForSending = ImplUserCRUD.getInstance().findByLogin(login).getPassword();
                    userFromDB.setPassword(passwordForSending);
                    SendEmail sendEmail = new SendEmail();
                    sendEmail.sendEmailToUSer(userFromDB);
                    System.out.println(6);
                    httpSession.setAttribute("validInput", true);
                    return new ResponseContext(JspPath.FORGOT_PASSWORD.getPath(), ResponseType.FORWARD);
                }
            } catch (Exception e) {
                System.out.println(5);
                e.printStackTrace();
                httpSession.setAttribute("InvalidInput", true);
                return new ResponseContext(JspPath.FORGOT_PASSWORD.getPath(), ResponseType.FORWARD);
            }
        }
        System.out.println(6);
        httpSession.setAttribute("wrongInput", true);
        return new ResponseContext(JspPath.FORGOT_PASSWORD.getPath(), ResponseType.FORWARD);
    }
}
