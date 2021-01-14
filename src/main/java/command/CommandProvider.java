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
    private Map<String, Command> commandMatching = new HashMap<>();

    private CommandProvider() {
//        commandMatching.put(CommandValue.SIGN_UP.getValue(), new SignUp());
//        commandMatching.put(CommandValue.SIGN_IN.getValue(), new SignIn());
        commandMatching.put(CommandValue.ERROR.getValue(), new ErrorInRequest());
        commandMatching.put(CommandValue.SIGN_IN_PAGE.getValue(), new SignInPage());
        commandMatching.put(CommandValue.REGISTRATION_PAGE.getValue(), new RegistrationPage());
//        commandMatching.put(CommandValue.ADMIN_MAP.getValue(), new AdminMap());
        commandMatching.put(CommandValue.SHOW_PARKING.getValue(), new ShowParking());
//        commandMatching.put(CommandValue.SEARCH.getValue(), new Search());
//        commandMatching.put(CommandValue.GO_TO_ABOUT_PAGE.getValue(), new GoToAboutPage());
        commandMatching.put(CommandValue.PARKING_PAGINATION_PAGE.getValue(), new ParkingPagination());
//        commandMatching.put(CommandValue.GO_TO_HELP_PAGE.getValue(), new GoToHelpPage());
        commandMatching.put(CommandValue.GO_TO_MAP_PAGE.getValue(), new GoToMapPage());
        commandMatching.put(CommandValue.GO_TO_MAIN_PAGE.getValue(), new GoToMainPage());
        commandMatching.put(CommandValue.ADMIN_PAGE.getValue(), new AdminPage());
//        commandMatching.put(CommandValue.SUGGEST.getValue(), new Suggest());
//        commandMatching.put(CommandValue.GO_TO_USER_ACCOUNT_PAGE.getValue(), new GoToUserAccountPage());
//        commandMatching.put(CommandValue.GO_TO_MAIN_PAGE.getValue(), new GoToMainPage());

    }

    public Command getCommand(HttpServletRequest request) {
        Command command;
        String commandParameter = request.getParameter("command");
        try {
            command = commandMatching.get(commandParameter);
        } catch (IllegalArgumentException e) {
            command = commandMatching.get(CommandValue.ERROR.getValue());
            request.setAttribute("error", e.getMessage());
//            logger.error((request.getSession().getAttribute(ParameterName.LOGIN.getValue())), e);
        }
        return command;
    }

}
