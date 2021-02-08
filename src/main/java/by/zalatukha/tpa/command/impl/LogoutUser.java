package by.zalatukha.tpa.command.impl;

import by.zalatukha.tpa.command.Command;
import by.zalatukha.tpa.command.JspPath;
import by.zalatukha.tpa.command.ResponseContext;
import by.zalatukha.tpa.command.ResponseType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutUser implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        request.getServletContext().setAttribute("isUserWithoutLogin", true);
        httpSession.removeAttribute("isUser");
        httpSession.removeAttribute("user");
        httpSession.removeAttribute("isAdmin");
        return new ResponseContext(JspPath.MAIN_PAGE.getPath(), ResponseType.REDIRECT);
    }
}
