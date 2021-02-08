package by.zalatukha.tpa.service;

import by.zalatukha.tpa.entity.ServicesProvidedByParking;

import javax.servlet.http.HttpServletRequest;

/**
 * Designed to process parameters coming from the admin page
 */
public class CheckboxesAdminPageService {
    public static ServicesProvidedByParking setService(HttpServletRequest request) {
        ServicesProvidedByParking newService = new ServicesProvidedByParking();
        if (request.getParameter("electricity") != null) {
            newService.setElectricity(true);
        }
        if (request.getParameter("fence") != null) {
            newService.setFence(true);
        }
        if (request.getParameter("food") != null) {
            newService.setFood(true);
        }
        if (request.getParameter("gasStation") != null) {
            newService.setGasStation(true);
        }
        if (request.getParameter("guard") != null) {
            newService.setGuardedParking(true);
        }
        if (request.getParameter("light") != null) {
            newService.setLighting(true);
        }
        if (request.getParameter("lodging") != null) {
            newService.setLodging(true);
        }
        if (request.getParameter("securityCameras") != null) {
            newService.setSecurityCameras(true);
        }
        if (request.getParameter("service") != null) {
            newService.setTruckService(true);
        }
        if (request.getParameter("shower") != null) {
            newService.setShower(true);
        }
        if (request.getParameter("store") != null) {
            newService.setStore(true);
        }
        if (request.getParameter("truckWash") != null) {
            newService.setTruckWash(true);
        }
        if (request.getParameter("water") != null) {
            newService.setWater(true);
        }
        if (request.getParameter("wc") != null) {
            newService.setWc(true);
        }
        if (request.getParameter("wifi") != null) {
            newService.setWifi(true);
        }
        return newService;
    }
}
