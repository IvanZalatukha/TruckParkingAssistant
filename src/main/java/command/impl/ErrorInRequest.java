package command.impl;

import command.Command;
import command.JspPath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorInRequest implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return JspPath.ERROR_PAGE.getPath();
    }
}
