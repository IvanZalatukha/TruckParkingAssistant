package dao.impl;

import dao.CRUDRepository;
import dao.Connector;
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
    private static final String CREATE = "INSERT INTO parking (id, name, spots_total, spots_currently, " +
            "coordinate_latitude, coordinate_longitude) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM parking WHERE id=?";
    private static final String UPDATE = "UPDATE parking SET name=?, spots_total=?, spots_currently=?," +
            "coordinate_latitude=?, coordinate_longitude=? WHERE id=?";
    private static final Connection connection = Connector.getConnection();

    public Parking findById(int id) {

        Parking parking = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultStatement = preparedStatement.executeQuery();
            if (resultStatement.next()) {
                parking = getParkingFromRS(resultStatement);
            }
            resultStatement.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return parking;
    }

    @Override
    public List findAll() {
    List<Parking> list = new ArrayList<>();
        Parking parking = null;
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
        parking.setSpotsCurrently(resultStatement.getInt("spots_currently"));
        parking.setCoordinateLatitude(resultStatement.getDouble("coordinate_latitude"));
        parking.setCoordinateLongitude(resultStatement.getDouble("coordinate_longitude"));
        return parking;
    }


    @Override
    public boolean create(Object obj) {
        Parking park = (Parking) obj;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setInt(1, park.getId());
            preparedStatement.setString(2, park.getName());
            preparedStatement.setInt(3, park.getSpotsTotal());
            preparedStatement.setInt(4, park.getSpotsCurrently());
            preparedStatement.setDouble(5, park.getCoordinateLatitude());
            preparedStatement.setDouble(6, park.getCoordinateLongitude());
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
            preparedStatement.close();
            if(i == 1) {
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
            preparedStatement.setInt(3, park.getSpotsCurrently());
            preparedStatement.setDouble(4, park.getCoordinateLatitude());
            preparedStatement.setDouble(5, park.getCoordinateLongitude());
            preparedStatement.setInt(6, id);
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
