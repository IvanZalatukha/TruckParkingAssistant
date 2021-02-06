package by.zalatukha.tpa.command.impl;

import by.zalatukha.tpa.command.Command;
import by.zalatukha.tpa.command.JspPath;
import by.zalatukha.tpa.command.ResponseContext;
import by.zalatukha.tpa.command.ResponseType;
import by.zalatukha.tpa.entity.Parking;
import by.zalatukha.tpa.service.GetAllParkingsFromDB;
import by.zalatukha.tpa.entity.ServicesProvidedByParking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GoToMapPage implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession();

        List<Parking> allParkings = GetAllParkingsFromDB.getAllParkingsFromDB();
        List<ServicesProvidedByParking> allServices = GetAllParkingsFromDB.getAllServicesFromDB();

        if(httpSession.getAttribute("showParking") != null){
            httpSession.removeAttribute("showParking");
        }
        httpSession.setAttribute("allServices", allServices);
        httpSession.setAttribute("allParkings", allParkings);

        return new ResponseContext(JspPath.MAP_PAGE.getPath(), ResponseType.REDIRECT);
    }
}
