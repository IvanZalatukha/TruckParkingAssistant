package by.zalatukha.tpa.service;

import by.zalatukha.tpa.entity.Parking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ParkingPaginationAdminPage {

    public static void pagination(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
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
        httpSession.setAttribute("noOfPages", nOfPages);
        httpSession.setAttribute("recordsPerPage", recordsPerPage);
    }
}
