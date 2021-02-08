package by.zalatukha.tpa.command.impl;

import by.zalatukha.tpa.command.Command;
import by.zalatukha.tpa.command.JspPath;
import by.zalatukha.tpa.command.ResponseContext;
import by.zalatukha.tpa.command.ResponseType;
import by.zalatukha.tpa.dao.impl.ImplParkingCRUD;
import by.zalatukha.tpa.dao.impl.ImplParkingsServicesCRUD;
import by.zalatukha.tpa.entity.Parking;
import by.zalatukha.tpa.entity.ServicesProvidedByParking;
import by.zalatukha.tpa.service.CheckboxesAdminPageService;
import by.zalatukha.tpa.util.LoggerUtil;
import by.zalatukha.tpa.validation.CoordinateValidate;
import by.zalatukha.tpa.validation.SpotsValidate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This class is a command for processing and displaying images
 * representing various services provided by parking place
 */
public class CheckboxesCommand implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request) {

        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String name = request.getParameter("name");
        String spots = request.getParameter("spots");
        HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute("updateService") != null) {
            int numberOfParking = (int) httpSession.getAttribute("numberOfParking");

            if (!longitude.isEmpty() && !latitude.isEmpty() && !spots.isEmpty() && !name.isEmpty()) {
                Parking newParking = new Parking();
                newParking.setName(name);
                newParking.setSpotsTotal(Integer.parseInt(spots));
                newParking.setCoordinateLatitude(Double.parseDouble(latitude));
                newParking.setCoordinateLongitude(Double.parseDouble(longitude));
                newParking.setId(ImplParkingCRUD.getInstance().findAll().size() + 1);
                ImplParkingCRUD.getInstance().update(numberOfParking, newParking);
            }
            ServicesProvidedByParking newService;
            newService = CheckboxesAdminPageService.setService(request);
            ImplParkingsServicesCRUD.getInstance().update(numberOfParking, newService);
            return new ResponseContext(JspPath.ADMIN_PAGE.getPath(), ResponseType.REDIRECT);
        }
        if (!checkForIncorrectInput(latitude, longitude, name, spots, httpSession)) {
            return new ResponseContext(JspPath.ADMIN_PAGE.getPath(), ResponseType.REDIRECT);
        }

        Parking newParking = new Parking();
        newParking.setName(name);
        newParking.setSpotsTotal(Integer.parseInt(spots));
        newParking.setCoordinateLatitude(Double.parseDouble(latitude));
        newParking.setCoordinateLongitude(Double.parseDouble(longitude));
        newParking.setId(ImplParkingCRUD.getInstance().findAll().size() + 1);

        ServicesProvidedByParking newService;
        newService = CheckboxesAdminPageService.setService(request);
        newService.setId(newParking.getId());

        ImplParkingCRUD.getInstance().create(newParking);
        ImplParkingsServicesCRUD.getInstance().create(newService);

        return new ResponseContext(JspPath.ADMIN_PAGE.getPath(), ResponseType.REDIRECT);
    }

    private boolean checkForIncorrectInput(String latitude, String longitude, String name, String spots, HttpSession httpSession) {
        if (longitude.isEmpty() || latitude.isEmpty() || spots.isEmpty() || name.isEmpty()) {
            httpSession.setAttribute("wrongInput", true);
            LoggerUtil.getInstance().error("Incorrect input, there are empty fields");
            return false;
        }
        CoordinateValidate coordinateValidate = new CoordinateValidate();
        if (!coordinateValidate.validate(longitude) || !coordinateValidate.validate(latitude)) {
            httpSession.setAttribute("wrongCoordinate", true);
            LoggerUtil.getInstance().error("Incorrect input, coordinates entered incorrectly");
            return false;
        }
        SpotsValidate spotsValidate = new SpotsValidate();
        if (!spotsValidate.validate(spots)) {
            httpSession.setAttribute("wrongSpots", true);
            LoggerUtil.getInstance().error("Incorrect input in the field number of parking spots");
            return false;
        }
        return true;
    }
}
