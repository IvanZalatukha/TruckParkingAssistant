package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInPage implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseContext(JspPath.SIGN_IN_PAGE.getPath(), ResponseType.FORWARD);
    }
}
