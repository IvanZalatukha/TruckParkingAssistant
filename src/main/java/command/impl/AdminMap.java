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

        if (httpSession.getAttribute("showParking") != null) {
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

            Parking showParking = allParkings.get(i - 1);
            httpSession.setAttribute("showParking", showParking);

            ServicesProvidedByParking updateService = allServices.get(i - 1);
            if (updateService.getFence()) {
                httpSession.setAttribute("fence", true);
            }
            if (updateService.getSecurityCameras()) {
                httpSession.setAttribute("securityCameras", true);
            }
            if (updateService.getWc()) {
                httpSession.setAttribute("wc", true);
            }
            if (updateService.getShower()) {
                httpSession.setAttribute("shower", true);
            }
            if (updateService.getGuardedParking()) {
                httpSession.setAttribute("guard", true);
            }
            if (updateService.getLighting()) {
                httpSession.setAttribute("light", true);
            }
            if (updateService.getElectricity()) {
                httpSession.setAttribute("electricity", true);
            }
            if (updateService.getWater()) {
                httpSession.setAttribute("water", true);
            }
            if (updateService.getGasStation()) {
                httpSession.setAttribute("gasStation", true);
            }
            if (updateService.getWifi()) {
                httpSession.setAttribute("wifi", true);
            }
            if (updateService.getLodging()) {
                httpSession.setAttribute("lodging", true);
            }
            if (updateService.getTruckService()) {
                httpSession.setAttribute("truckService", true);
            }
            if (updateService.getTruckWash()) {
                httpSession.setAttribute("truckWash", true);
            }
            if (updateService.getStore()) {
                httpSession.setAttribute("store", true);
            }
            if (updateService.getFood()) {
                httpSession.setAttribute("food", true);
            }


            httpSession.setAttribute("numberOfParking", i);
            httpSession.setAttribute("updateService", null);
            httpSession.setAttribute("updateButton", true);
        }


        httpSession.setAttribute("noOfPages", nOfPages);
        httpSession.setAttribute("recordsPerPage", recordsPerPage);
        return new ResponseContext(JspPath.ADMIN_PAGE.getPath(), ResponseType.REDIRECT);
    }
}
