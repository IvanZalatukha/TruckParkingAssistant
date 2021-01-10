package command.impl;

import command.Command;
import command.JspPath;
import command.ParameterName;
import dao.impl.ImplUserCRUD;
import domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToMainPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        return JspPath.MAIN_PAGE.getPath();
    }
}
