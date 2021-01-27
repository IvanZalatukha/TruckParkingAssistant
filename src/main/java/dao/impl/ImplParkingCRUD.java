package dao.impl;

import dao.CRUDRepository;
import dao.pool.ConnectionPool;
import domain.Parking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplParkingCRUD implements CRUDRepository {
    private static final String FIND_BY_ID = "SELECT * FROM parking WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM parking ORDER BY id";
    private static final String CREATE = "INSERT INTO parking (id, name, spots_total, coordinate_latitude, " +
            "coordinate_longitude) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM parking WHERE id=?";
    private static final String UPDATE = "UPDATE parking SET name=?, spots_total=?, coordinate_latitude=?, " +
            "coordinate_longitude=? WHERE id=?";
    private static final String FIND_FOR_PAGINATION = "SELECT * FROM parking LIMIT ?, ?";
    private static final String NUMBER_OF_PARKINGS = "SELECT COUNT(id) FROM parking";
    private static Connection connection = null;

    static {
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static ImplParkingCRUD instance;

    public static ImplParkingCRUD getInstance() {
        if (instance == null) {
            instance = new ImplParkingCRUD();
        }
        return instance;
    }

    public Parking findById(int id) {

        Parking parking = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultStatement = preparedStatement.executeQuery();
            if (resultStatement.next()) {
                parking = getParkingFromRS(resultStatement);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return parking;
    }

    @Override
    public List<Parking> findAll() {
        List<Parking> list = new ArrayList<>();
        Parking parking;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultStatement = preparedStatement.executeQuery();
            while (resultStatement.next()) {
                parking = getParkingFromRS(resultStatement);
                list.add(parking);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    private Parking getParkingFromRS(ResultSet resultStatement) throws SQLException {
        Parking parking = new Parking();
        parking.setId(resultStatement.getInt("id"));
        parking.setName(resultStatement.getString("name"));
        parking.setSpotsTotal(resultStatement.getInt("spots_total"));
        parking.setCoordinateLatitude(resultStatement.getDouble("coordinate_latitude"));
        parking.setCoordinateLongitude(resultStatement.getDouble("coordinate_longitude"));
        return parking;
    }

    public List<Parking> findForPagination(int start, int recordsPerPage) {
        List<Parking> list = new ArrayList<>();
        Parking parking;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_FOR_PAGINATION);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, recordsPerPage);
            ResultSet resultStatement = preparedStatement.executeQuery();
            while (resultStatement.next()) {
                parking = getParkingFromRS(resultStatement);
                list.add(parking);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public Integer numberOfParkings() {
        Integer number = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(NUMBER_OF_PARKINGS);
            ResultSet resultStatement = preparedStatement.executeQuery();
            while (resultStatement.next()) {
                return resultStatement.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return number;
    }

    @Override
    public boolean create(Object obj) {
        Parking park = (Parking) obj;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setInt(1, park.getId());
            preparedStatement.setString(2, park.getName());
            preparedStatement.setInt(3, park.getSpotsTotal());
            preparedStatement.setDouble(4, park.getCoordinateLatitude());
            preparedStatement.setDouble(5, park.getCoordinateLongitude());
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean update(int id, Object obj) {
        Parking park = (Parking) obj;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, park.getName());
            preparedStatement.setInt(2, park.getSpotsTotal());
            preparedStatement.setDouble(3, park.getCoordinateLatitude());
            preparedStatement.setDouble(4, park.getCoordinateLongitude());
            preparedStatement.setInt(5, id);
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


}
