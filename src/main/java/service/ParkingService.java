package service;

import dao.impl.ImplParkingCRUD;
import domain.Parking;

import java.util.List;

public class ParkingService {

    public List<Parking> findParkings(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        return ImplParkingCRUD.getInstance().findForPagination(start, recordsPerPage);
    }

    public Integer getNumberOfRows() {
        return ImplParkingCRUD.getInstance().numberOfParkings();
    }
}
