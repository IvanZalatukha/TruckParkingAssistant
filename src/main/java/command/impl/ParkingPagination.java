package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;
import entity.Parking;
import service.ParkingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ParkingPagination implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

        ParkingService parkingService = new ParkingService();

        List<Parking> parkings = parkingService.findAmountOfParkings(currentPage, recordsPerPage);
        HttpSession httpSession = request.getSession();
        request.setAttribute("parkings", parkings);

        int rows = parkingService.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {

            nOfPages++;
        }

        httpSession.setAttribute("noOfPages", nOfPages);
        httpSession.setAttribute("currentPage", currentPage);
        httpSession.setAttribute("recordsPerPage", recordsPerPage);
        return new ResponseContext(JspPath.PARKING_PAGINATION_PAGE.getPath(), ResponseType.FORWARD);

    }
}
