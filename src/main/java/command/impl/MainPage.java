package command.impl;

import command.*;
import dao.impl.ImplUserCRUD;
import domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPage implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseContext(JspPath.MAIN_PAGE.getPath(), ResponseType.FORWARD);

    }
}
