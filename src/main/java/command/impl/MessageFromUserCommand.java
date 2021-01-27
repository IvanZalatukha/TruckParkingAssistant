package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;
import dao.impl.ImplMessageCRUD;
import domain.MessageFromUser;
import validation.EmailValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MessageFromUserCommand implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession httpSession = request.getSession();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String topic = request.getParameter("topic");
        String text = request.getParameter("text");

        if (name != null && email != null && !text.isEmpty()) {
            EmailValidation emailValidation = new EmailValidation();
            if (!emailValidation.validate(email)) {
                httpSession.setAttribute("wrongEmailInput", true);
                return new ResponseContext(JspPath.CONNECT_WITH_US_PAGE.getPath(), ResponseType.FORWARD);
            }

            MessageFromUser message = new MessageFromUser(name, email, topic, text);
            message.setId(ImplMessageCRUD.getInstance().numberOfMessages() + 1);
            ImplMessageCRUD.getInstance().create(message);
            httpSession.setAttribute("validInput", true);
            return new ResponseContext(JspPath.CONNECT_WITH_US_PAGE.getPath(), ResponseType.FORWARD);
        }

        httpSession.setAttribute("wrongInput", true);
        return new ResponseContext(JspPath.CONNECT_WITH_US_PAGE.getPath(), ResponseType.FORWARD);
    }
}
