package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutUser implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession();
        request.getServletContext().setAttribute("isUserWithoutLogin", true);
        httpSession.removeAttribute("isUser");
        httpSession.removeAttribute("user");
        httpSession.removeAttribute("isAdmin");
        return new ResponseContext(JspPath.MAIN_PAGE.getPath(), ResponseType.REDIRECT);
    }
}
