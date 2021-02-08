package by.zalatukha.tpa.command.impl;

import by.zalatukha.tpa.command.Command;
import by.zalatukha.tpa.command.JspPath;
import by.zalatukha.tpa.command.ResponseContext;
import by.zalatukha.tpa.command.ResponseType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LocaleSwitch implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("locale") == null || session.getAttribute("locale").equals("en_US")) {
            session.setAttribute("locale", "ru_Ru");
            return new ResponseContext(JspPath.MAIN_PAGE.getPath(), ResponseType.REDIRECT);
        }
        session.setAttribute("locale", "en_US");
        return new ResponseContext(JspPath.MAIN_PAGE.getPath(), ResponseType.REDIRECT);
    }
}
