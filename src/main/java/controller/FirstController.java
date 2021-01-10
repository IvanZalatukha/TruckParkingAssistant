package controller;

import command.Command;
import command.CommandProvider;
import command.JspPath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller",
        urlPatterns = {"/login", ""})
public class FirstController extends HttpServlet {

    public FirstController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("method DO GEEET servleta");
        System.out.println(request.getParameter("command"));

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("method DO POOOST servleta");
        System.out.println(request.getParameter("command"));

        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = CommandProvider.getInstance().getCommand(request);
        try {
            String page = command.execute(request, response);

            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);

        }catch (NullPointerException e) {
            System.out.println("exception");
            RequestDispatcher dispatcher = request.getRequestDispatcher(JspPath.MAIN_PAGE.getPath());
            dispatcher.forward(request, response);
        }
    }
}
