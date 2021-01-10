package command.impl;

import command.Command;
import command.JspPath;
import dao.impl.ImplParkingCRUD;
import dao.impl.ImplUserCRUD;
import domain.Parking;
import domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class GoToMapPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List allParkings = ImplParkingCRUD.getInstance().findAll();
        Parking park = ImplParkingCRUD.getInstance().findById(7);


        request.setAttribute("name", "Nick");
        request.setAttribute("age", 777);
        request.setAttribute("parkings", allParkings);
        request.setAttribute("parking", park);
        System.out.println("set  MAAAAPPP attribute");


        return JspPath.MAP_PAGE.getPath();
    }
}
