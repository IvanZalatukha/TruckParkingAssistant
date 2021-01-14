package command.impl;

import command.Command;
import command.JspPath;
import dao.impl.ImplParkingCRUD;
import dao.impl.ImplParkingsServicesCRUD;
import domain.Parking;
import domain.ServicesProvidedByParking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowParking implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            Parking showPark = ImplParkingCRUD.getInstance().findById(id);
            ServicesProvidedByParking showServices = ImplParkingsServicesCRUD.getInstance().findById(id);
            request.setAttribute("showPark", showPark);
            request.setAttribute("showServices", showServices);
            return JspPath.PARKING_PAGINATION_PAGE.getPath();
        } return JspPath.PARKING_PAGINATION_PAGE.getPath();
    }
}
