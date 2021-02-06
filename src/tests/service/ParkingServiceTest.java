package service;

import by.zalatukha.tpa.dao.impl.ImplParkingCRUD;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingServiceTest {

    @Test
    @DisplayName("should return amount of parkings")
    void findAmountOfParkings() {
    }

    @Test
    @DisplayName("Simple method which should return amount of parkings")
    void getNumberOfRows() {
        assertEquals(ImplParkingCRUD.getInstance().numberOfParkings(),
                ImplParkingCRUD.getInstance().numberOfParkings());
    }
}