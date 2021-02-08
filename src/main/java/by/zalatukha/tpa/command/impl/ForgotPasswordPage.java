package by.zalatukha.tpa.command.impl;

import by.zalatukha.tpa.command.Command;
import by.zalatukha.tpa.command.JspPath;
import by.zalatukha.tpa.command.ResponseContext;
import by.zalatukha.tpa.command.ResponseType;

import javax.servlet.http.HttpServletRequest;

public class ForgotPasswordPage implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request) {
        return new ResponseContext(JspPath.FORGOT_PASSWORD.getPath(), ResponseType.REDIRECT);
    }
}
