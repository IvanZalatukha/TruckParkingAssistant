package command.impl;

import command.Command;
import command.JspPath;
import command.ResponseContext;
import command.ResponseType;
import dao.impl.ImplParkingCRUD;
import dao.impl.ImplParkingsServicesCRUD;
import domain.Parking;
import domain.ServicesProvidedByParking;
import validation.CoordinateValidate;
import validation.NameValidation;
import validation.SpotsValidate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Checkboxes implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request, HttpServletResponse response) {

        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String name = request.getParameter("name");
        String spots = request.getParameter("spots");
        HttpSession httpSession = request.getSession();

        if (longitude.isEmpty() || latitude.isEmpty() || spots.isEmpty() || name.isEmpty()) {
            httpSession.setAttribute("wrongInput", true);
            return new ResponseContext(JspPath.ADMIN_PAGE.getPath(), ResponseType.FORWARD);
        }
        CoordinateValidate coordinateValidate = new CoordinateValidate();
        if (!coordinateValidate.validate(longitude) || !coordinateValidate.validate(latitude)) {
            httpSession.setAttribute("wrongCoordinate", true);
            return new ResponseContext(JspPath.ADMIN_PAGE.getPath(), ResponseType.FORWARD);
        }
        SpotsValidate spotsValidate = new SpotsValidate();
        if (!spotsValidate.validate(spots)) {
            httpSession.setAttribute("wrongSpots", true);
            return new ResponseContext(JspPath.ADMIN_PAGE.getPath(), ResponseType.FORWARD);
        }

        Parking newParking = new Parking();
        newParking.setName(name);
        newParking.setSpotsTotal(Integer.parseInt(spots));
        newParking.setCoordinateLatitude(Double.parseDouble(latitude));
        newParking.setCoordinateLongitude(Double.parseDouble(longitude));
        newParking.setId(ImplParkingCRUD.getInstance().numberOfParkings() + 1);

        ServicesProvidedByParking newService = new ServicesProvidedByParking();
        newService = setService(request, newService);
        newService.setId(newParking.getId());

        ImplParkingCRUD.getInstance().create(newParking);
        ImplParkingsServicesCRUD.getInstance().create(newService);


        return new ResponseContext(JspPath.ADMIN_PAGE.getPath(), ResponseType.FORWARD);
    }
    public ServicesProvidedByParking setService(HttpServletRequest request, ServicesProvidedByParking newService) {

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
