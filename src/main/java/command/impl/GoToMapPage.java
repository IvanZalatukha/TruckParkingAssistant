package command.impl;

import command.Command;
import command.JspPath;
import dao.impl.ImplParkingCRUD;
import dao.impl.ImplParkingsServicesCRUD;
import dao.impl.ImplUserCRUD;
import domain.Parking;
import domain.ServicesProvidedByParking;
import domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class GoToMapPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Parking> allParkings = ImplParkingCRUD.getInstance().findAll();
        List<ServicesProvidedByParking> allServices = ImplParkingsServicesCRUD.getInstance().findAll();



        request.setAttribute("allServices", allServices);
        request.setAttribute("allParkings", allParkings);

        System.out.println("set  MAAAAPPP attribute");


        return JspPath.MAP_PAGE.getPath();
    }
}
