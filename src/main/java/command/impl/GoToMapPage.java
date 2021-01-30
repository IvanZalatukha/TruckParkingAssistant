package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;
import dao.impl.ImplParkingCRUD;
import dao.impl.ImplParkingsServicesCRUD;
import entity.Parking;
import service.SetRandomNumberOfCurrentSpots;
import entity.ServicesProvidedByParking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GoToMapPage implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession();

        if(httpSession.getAttribute("showParking") != null){
            httpSession.removeAttribute("showParking");
        }


        List<Parking> allParkings = SetRandomNumberOfCurrentSpots.setCurrentSpots(
                ImplParkingCRUD.getInstance().findAll());

        List<ServicesProvidedByParking> allServices = ImplParkingsServicesCRUD.getInstance().findAll();

        for (int i = 0; i < allParkings.size(); i++) {
            allParkings.get(i).setParkingServices(allServices.get(i));
        }



        httpSession.setAttribute("allServices", allServices);
        httpSession.setAttribute("allParkings", allParkings);


        return new ResponseContext(JspPath.MAP_PAGE.getPath(), ResponseType.FORWARD);
    }
}
