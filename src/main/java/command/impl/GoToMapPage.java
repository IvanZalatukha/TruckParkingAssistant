package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;
import dao.impl.ImplParkingCRUD;
import dao.impl.ImplParkingsServicesCRUD;
import domain.Parking;
import service.SetRandomNumberOfCurrentSpots;
import domain.ServicesProvidedByParking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GoToMapPage implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {

        List<Parking> allParkings = SetRandomNumberOfCurrentSpots.setCurrentSpots(
                ImplParkingCRUD.getInstance().findAll());

        List<ServicesProvidedByParking> allServices = ImplParkingsServicesCRUD.getInstance().findAll();
        HttpSession httpSession = request.getSession();

        httpSession.setAttribute("allServices", allServices);
        httpSession.setAttribute("allParkings", allParkings);

        System.out.println("set  MAAAAPPP attribute");
        return new ResponseContext(JspPath.MAP_PAGE.getPath(), ResponseType.FORWARD);
    }
}
