package by.zalatukha.tpa.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    ResponseContext execute(HttpServletRequest request, HttpServletResponse response);
}
