package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;
import dao.impl.ImplParkingCRUD;
import dao.impl.ImplParkingsServicesCRUD;
import domain.Parking;
import domain.ServicesProvidedByParking;
import service.ParkingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminMap implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {

        List<Parking> allParkings = ImplParkingCRUD.getInstance().findAll();
        List<ServicesProvidedByParking> allServices = ImplParkingsServicesCRUD.getInstance().findAll();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("allServices", allServices);
        httpSession.setAttribute("allParkings", allParkings);

        int currentPage = 1;
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

        httpSession.setAttribute("noOfPages", nOfPages);
        httpSession.setAttribute("currentPage", currentPage);
        httpSession.setAttribute("recordsPerPage", recordsPerPage);
        return new ResponseContext(JspPath.ADMIN_MAP_PAGE.getPath(), ResponseType.FORWARD);
    }
}
