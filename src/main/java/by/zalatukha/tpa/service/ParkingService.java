package by.zalatukha.tpa.service;

import by.zalatukha.tpa.dao.impl.ImplParkingCRUD;
import by.zalatukha.tpa.entity.Parking;

import java.util.List;

public class ParkingService {

    public List<Parking> findAmountOfParkings(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        return ImplParkingCRUD.getInstance().findForPagination(start, recordsPerPage);
    }

    public Integer getNumberOfRows() {
        return ImplParkingCRUD.getInstance().numberOfParkings();
    }


}
