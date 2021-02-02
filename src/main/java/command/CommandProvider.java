package command;

import command.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static CommandProvider instance;

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }
    private final Map<String, Command> commandMatching = new HashMap<>();

    private CommandProvider() {
        commandMatching.put(CommandValue.ERROR.getValue(), new ErrorInRequestPage());
        commandMatching.put(CommandValue.SIGN_IN_PAGE.getValue(), new SignInPage());
        commandMatching.put(CommandValue.SIGN_OUT_PAGE.getValue(), new LogoutUser());
        commandMatching.put(CommandValue.LOGIN_USER.getValue(), new LoginUser());
        commandMatching.put(CommandValue.ADMIN_MAP.getValue(), new AdminMap());
        commandMatching.put(CommandValue.ADMIN_PAGE.getValue(), new AdminMap());
        commandMatching.put(CommandValue.PARKING_PAGINATION_PAGE.getValue(), new ParkingPagination());
        commandMatching.put(CommandValue.GO_TO_MAP_PAGE.getValue(), new GoToMapPage());
        commandMatching.put(CommandValue.GO_TO_MAIN_PAGE.getValue(), new MainPage());
        commandMatching.put(CommandValue.CONNECT_WITH_US.getValue(), new ConnectWithUsPage());
        commandMatching.put(CommandValue.MESSAGE_FROM_USER.getValue(), new MessageFromUserCommand());
        commandMatching.put(CommandValue.OUR_SERVICE.getValue(), new OurServicePage());
        commandMatching.put(CommandValue.REGISTRATION_PAGE.getValue(), new RegistrationPage());
        commandMatching.put(CommandValue.REGISTRATION_USER.getValue(), new RegistrationUser());
        commandMatching.put(CommandValue.FORGOT_PASSWORD.getValue(), new ForgotPassword());
        commandMatching.put(CommandValue.FORGOT_PASSWORD_PAGE.getValue(), new ForgotPasswordPage());
        commandMatching.put(CommandValue.CHECKBOXES.getValue(), new Checkboxes());
        commandMatching.put(CommandValue.SWITCH_LOCALE.getValue(), new LocaleSwitch());
    }

    public Command getCommand(HttpServletRequest request) {
        Command command;
        String commandParameter = request.getParameter("command");
        try {
            command = commandMatching.get(commandParameter);
        } catch (IllegalArgumentException e) {
            command = commandMatching.get(CommandValue.ERROR.getValue());
            request.setAttribute("error", e.getMessage());
        }
        return command;
    }

}
