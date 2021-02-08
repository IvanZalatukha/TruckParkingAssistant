package by.zalatukha.tpa.command.impl;

import by.zalatukha.tpa.command.Command;
import by.zalatukha.tpa.command.JspPath;
import by.zalatukha.tpa.command.ResponseContext;
import by.zalatukha.tpa.command.ResponseType;
import by.zalatukha.tpa.entity.Parking;
import by.zalatukha.tpa.entity.ServicesProvidedByParking;
import by.zalatukha.tpa.service.GetAllParkingsFromDB;
import by.zalatukha.tpa.service.ParkingPaginationAdminPage;
import by.zalatukha.tpa.service.ShowUpdateDeleteAdminPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This class is a command is intended to display UI admin map
 */
public class AdminMapCommand implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request) {

        List<Parking> allParkings = GetAllParkingsFromDB.getAllParkingsFromDB();
        List<ServicesProvidedByParking> allServices = GetAllParkingsFromDB.getAllServicesFromDB();

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("allServices", allServices);
        httpSession.setAttribute("allParkings", allParkings);

        ParkingPaginationAdminPage.pagination(request);
        ShowUpdateDeleteAdminPage.showUpdateDelete(request, allParkings, allServices);

        return new ResponseContext(JspPath.ADMIN_PAGE.getPath(), ResponseType.REDIRECT);
    }
}
