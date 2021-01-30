package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConnectWithUsPage implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseContext(JspPath.CONNECT_WITH_US_PAGE.getPath(), ResponseType.FORWARD);
    }
}
