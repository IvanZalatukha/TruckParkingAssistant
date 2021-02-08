package by.zalatukha.tpa.controller;

import by.zalatukha.tpa.command.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/jsp/controller")
public class FirstController extends HttpServlet {

    public FirstController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = CommandProvider.getInstance().getCommand(request);
            if(request.getParameter("command") != null) {
                ResponseContext page = command.execute(request);
                if (page.getResponseType() == ResponseType.FORWARD) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(page.getPage());
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect(page.getPage());
                }
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(JspPath.MAIN_PAGE.getPath());
                dispatcher.forward(request, response);
            }
    }
}
