package by.zalatukha.tpa.command;

import by.zalatukha.tpa.command.impl.*;
import by.zalatukha.tpa.util.LoggerUtil;

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
        commandMatching.put(CommandValue.SIGN_OUT_PAGE.getValue(), new LogoutUserCommand());
        commandMatching.put(CommandValue.LOGIN_USER.getValue(), new LoginUserCommand());
        commandMatching.put(CommandValue.ADMIN_MAP.getValue(), new AdminMapCommand());
        commandMatching.put(CommandValue.PARKING_PAGINATION_PAGE.getValue(), new ParkingPaginationCommand());
        commandMatching.put(CommandValue.GO_TO_MAP_PAGE.getValue(), new MapCommand());
        commandMatching.put(CommandValue.GO_TO_MAIN_PAGE.getValue(), new MainPage());
        commandMatching.put(CommandValue.CONNECT_WITH_US.getValue(), new ConnectWithUsPage());
        commandMatching.put(CommandValue.MESSAGE_FROM_USER.getValue(), new MessageFromUserCommand());
        commandMatching.put(CommandValue.OUR_SERVICE.getValue(), new OurServicePage());
        commandMatching.put(CommandValue.REGISTRATION_PAGE.getValue(), new RegistrationPage());
        commandMatching.put(CommandValue.REGISTRATION_USER.getValue(), new RegistrationUserCommand());
        commandMatching.put(CommandValue.FORGOT_PASSWORD.getValue(), new ForgotPasswordCommand());
        commandMatching.put(CommandValue.FORGOT_PASSWORD_PAGE.getValue(), new ForgotPasswordPage());
        commandMatching.put(CommandValue.CHECKBOXES.getValue(), new CheckboxesCommand());
        commandMatching.put(CommandValue.SWITCH_LOCALE.getValue(), new LocaleSwitchCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        Command command;
        try {
            String commandParameter = request.getParameter("command");
            command = commandMatching.get(commandParameter);
        } catch (IllegalArgumentException e) {
            command = commandMatching.get(CommandValue.ERROR.getValue());
            request.setAttribute("error", e.getMessage());
            LoggerUtil.getInstance().error("Something went wrong " + e);
        }
        return command;
    }

}
