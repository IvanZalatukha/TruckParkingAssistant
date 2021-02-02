package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;
import dao.impl.ImplParkingCRUD;
import dao.impl.ImplParkingsServicesCRUD;
import entity.Parking;
import entity.ServicesProvidedByParking;
import service.ParkingService;
import service.SetRandomNumberOfCurrentSpots;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminMap implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {

        List<Parking> allParkings = SetRandomNumberOfCurrentSpots.setCurrentSpots(
                ImplParkingCRUD.getInstance().findAll());

        List<ServicesProvidedByParking> allServices = ImplParkingsServicesCRUD.getInstance().findAll();

        for (int i = 0; i < allParkings.size(); i++) {
            allParkings.get(i).setParkingServices(allServices.get(i));
        }

        HttpSession httpSession = request.getSession();

        httpSession.setAttribute("allServices", allServices);
        httpSession.setAttribute("allParkings", allParkings);

        if(httpSession.getAttribute("showParking") != null){
            httpSession.removeAttribute("showParking");
        }
        int currentPage = 1;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        int recordsPerPage = 5;

        if (request.getParameter("currentPage") != null || request.getParameter("recordsPerPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }

        ParkingService parkingService = new ParkingService();

        List<Parking> parkings = parkingService.findAmountOfParkings(currentPage, recordsPerPage);

        httpSession.setAttribute("parkings", parkings);

        int rows = parkingService.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {

            nOfPages++;
        }
        httpSession.setAttribute("currentPage", currentPage);

        if (request.getParameter("id") != null) {
            int i = Integer.parseInt(request.getParameter("id"));
            Parking showParking = allParkings.get(i - 1);

            httpSession.setAttribute("currentPage", currentPage);
            httpSession.setAttribute("showParking", showParking);
        }
        if (request.getParameter("delete") != null) {
            int i = Integer.parseInt(request.getParameter("delete"));
            ImplParkingCRUD.getInstance().delete(i);
        }
        if (request.getParameter("update") != null) {
            int i = Integer.parseInt(request.getParameter("update"));
            Parking updateParking = allParkings.get(i - 1);
        }


        httpSession.setAttribute("noOfPages", nOfPages);
        httpSession.setAttribute("recordsPerPage", recordsPerPage);
        return new ResponseContext(JspPath.ADMIN_PAGE.getPath(), ResponseType.REDIRECT);
    }
}
