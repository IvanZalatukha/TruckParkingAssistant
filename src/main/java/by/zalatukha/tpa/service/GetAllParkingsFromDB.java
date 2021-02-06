package by.zalatukha.tpa.service;

import by.zalatukha.tpa.dao.impl.ImplParkingCRUD;
import by.zalatukha.tpa.dao.impl.ImplParkingsServicesCRUD;
import by.zalatukha.tpa.entity.Parking;
import by.zalatukha.tpa.entity.ServicesProvidedByParking;

import java.util.List;

public class GetAllParkingsFromDB {

    public static List<Parking> getAllParkingsFromDB() {
        List<Parking> allParkings = SetRandomNumberOfCurrentSpots.setCurrentSpots(
                ImplParkingCRUD.getInstance().findAll());

        List<ServicesProvidedByParking> allServices = getAllServicesFromDB();

        for (int i = 0; i < allParkings.size(); i++) {
            allParkings.get(i).setParkingServices(allServices.get(i));
        }
        return allParkings;
    }
    public static List<ServicesProvidedByParking> getAllServicesFromDB() {
        return ImplParkingsServicesCRUD.getInstance().findAll();
    }
}
