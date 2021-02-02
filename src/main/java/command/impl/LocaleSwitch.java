package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LocaleSwitch implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();


        if (session.getAttribute("locale") == null || session.getAttribute("locale").equals("en_US")) {
            session.setAttribute("locale", "ru_Ru");
            return new ResponseContext(JspPath.MAIN_PAGE.getPath(), ResponseType.REDIRECT);
        }
        session.setAttribute("locale", "en_US");
        System.out.println();

//        session.setAttribute("locale", "en_US");
//        session.setAttribute("locale", "ru_RU");


        return new ResponseContext(JspPath.MAIN_PAGE.getPath(), ResponseType.REDIRECT);
    }
}
