package by.zalatukha.tpa.command.impl;

import by.zalatukha.tpa.command.Command;
import by.zalatukha.tpa.command.JspPath;
import by.zalatukha.tpa.command.ResponseContext;
import by.zalatukha.tpa.command.ResponseType;
import by.zalatukha.tpa.dao.impl.ImplMessageCRUD;
import by.zalatukha.tpa.entity.MessageFromUser;
import by.zalatukha.tpa.util.LoggerUtil;
import by.zalatukha.tpa.validation.EmailValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This class is a command for receiving and saving messages from the user to the database
 */
public class MessageFromUserCommand implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String topic = request.getParameter("topic");
        String text = request.getParameter("text");

        if (!name.isEmpty() && !email.isEmpty() && !text.isEmpty()) {
            EmailValidation emailValidation = new EmailValidation();
            if (!emailValidation.validate(email)) {
                httpSession.setAttribute("wrongEmailInput", true);
                return new ResponseContext(JspPath.CONNECT_WITH_US_PAGE.getPath(), ResponseType.REDIRECT);
            }

            MessageFromUser message = new MessageFromUser(name, email, topic, text);
            message.setId(ImplMessageCRUD.getInstance().numberOfMessages() + 1);
            ImplMessageCRUD.getInstance().create(message);
            httpSession.setAttribute("validInput", true);
            LoggerUtil.getInstance().info("User "+ name + " sent us a message");
            return new ResponseContext(JspPath.CONNECT_WITH_US_PAGE.getPath(), ResponseType.REDIRECT);
        }
        LoggerUtil.getInstance().error("Incorrect input, there are empty fields");

        httpSession.setAttribute("wrongInput", true);
        return new ResponseContext(JspPath.CONNECT_WITH_US_PAGE.getPath(), ResponseType.REDIRECT);
    }
}
