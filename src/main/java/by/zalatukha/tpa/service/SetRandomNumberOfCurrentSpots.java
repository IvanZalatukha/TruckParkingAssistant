package by.zalatukha.tpa.service;

import by.zalatukha.tpa.entity.Parking;

import java.util.List;
import java.util.Random;

/**
 * The class is designed to simulate the existence of trucks in the parking places
 */
public class SetRandomNumberOfCurrentSpots {

    public static List<Parking> setCurrentSpots(List<Parking> list) {

        for (Parking park : list) {
            int randomAmountSpots = SetRandomNumberOfCurrentSpots.generateRandomNumber(park.getSpotsTotal());
            park.setSpotsCurrently(randomAmountSpots);
        }
        return list;
    }

    public static int generateRandomNumber(int limit) {
        Random random = new Random();
        return random.nextInt(limit);
    }
}
