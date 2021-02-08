package by.zalatukha.tpa.service;

import by.zalatukha.tpa.dao.impl.ImplParkingCRUD;
import by.zalatukha.tpa.entity.Parking;
import by.zalatukha.tpa.entity.ServicesProvidedByParking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Designed to process parameters coming from the admin page
 */
public class ShowUpdateDeleteAdminPage {
    public static void showUpdateDelete(HttpServletRequest request, List<Parking> allParkings, List<ServicesProvidedByParking> allServices) {
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("showParking") != null) {
            httpSession.removeAttribute("showParking");
        }

        if (request.getParameter("id") != null) {
            int i = Integer.parseInt(request.getParameter("id"));
            Parking showParking = allParkings.get(i - 1);

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
    }
}
