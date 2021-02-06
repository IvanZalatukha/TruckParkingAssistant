package by.zalatukha.tpa.util;

import by.zalatukha.tpa.controller.FirstController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {

    static Logger logger = LogManager.getLogger(FirstController.class);

    public static Logger getInstance() {
        return logger;
    }
}