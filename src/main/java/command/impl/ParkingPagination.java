package command.impl;

import command.Command;
import command.JspPath;
import domain.Parking;
import service.ParkingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ParkingPagination implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

        ParkingService parkingService = new ParkingService();

        List<Parking> parkings = parkingService.findParkings(currentPage, recordsPerPage);
        request.setAttribute("parkings", parkings);

        int rows = parkingService.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {

            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        return JspPath.PARKING_PAGINATION_PAGE.getPath();
    }
}
