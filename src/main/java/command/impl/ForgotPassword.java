package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;
import dao.impl.ImplUserCRUD;
import domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ForgotPassword implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {
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
                return new ResponseContext(JspPath.FORGOT_PASSWORD.getPath(), ResponseType.FORWARD);
            }
            try {
                if (userFromDB.getEmail().equals(email)) {
                    String passwordForSending = ImplUserCRUD.getInstance().findByLogin(login).getPassword();
                    httpSession.setAttribute("validInput", true);
                    return new ResponseContext(JspPath.FORGOT_PASSWORD.getPath(), ResponseType.FORWARD);
                }
            } catch (Exception e) {
                httpSession.setAttribute("InvalidInput", true);
                return new ResponseContext(JspPath.FORGOT_PASSWORD.getPath(), ResponseType.FORWARD);
            }
        }

        httpSession.setAttribute("wrongInput", true);
        return new ResponseContext(JspPath.FORGOT_PASSWORD.getPath(), ResponseType.FORWARD);
    }
}
