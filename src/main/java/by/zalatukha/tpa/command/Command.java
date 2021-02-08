package by.zalatukha.tpa.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    ResponseContext execute(HttpServletRequest request);
}
