package by.zalatukha.tpa.dao.impl;

import by.zalatukha.tpa.dao.CRUDRepository;
import by.zalatukha.tpa.dao.pool.ConnectionPool;
import by.zalatukha.tpa.entity.ServicesProvidedByParking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplParkingsServicesCRUD implements CRUDRepository {
    private static final String FIND_BY_ID = "SELECT * FROM services WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM services ORDER BY id";
    private static final String CREATE = "INSERT INTO services (id, fence, security_cameras, wc, " +
            "shower, guarded_parking, lighting, electricity, water, gas_station, wifi, lodging, truck_service," +
            "truck_wash, store, food ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM services WHERE id=?";
    private static final String UPDATE = "UPDATE services SET fence=?, security_cameras=?, wc=?, shower=?, guarded_parking=?, " +
            "lighting=?, electricity=?, water=?, gas_station=?, wifi=?, lodging=?, truck_service=?, truck_wash=?, store=?, food=? WHERE id=?";
    private static final String AMOUNT_ID = "SELECT COUNT(id) FROM services";
    private static Connection connection = null;

    static {
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static ImplParkingsServicesCRUD instance;

    public static ImplParkingsServicesCRUD getInstance() {
        if (instance == null) {
            instance = new ImplParkingsServicesCRUD();
        }
        return instance;
    }

    public ServicesProvidedByParking findById(int id) {

        ServicesProvidedByParking servicesProvidedByParking = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultStatement = preparedStatement.executeQuery();
            if (resultStatement.next()) {
                servicesProvidedByParking = getServiceFromRS(resultStatement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return servicesProvidedByParking;
    }

    @Override
    public List<ServicesProvidedByParking> findAll() {
        List<ServicesProvidedByParking> list = new ArrayList<>();
        ServicesProvidedByParking servicesProvidedByParking;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultStatement = preparedStatement.executeQuery();
            while (resultStatement.next()) {
                servicesProvidedByParking = getServiceFromRS(resultStatement);
                list.add(servicesProvidedByParking);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    private ServicesProvidedByParking getServiceFromRS(ResultSet resultStatement) throws SQLException {
        ServicesProvidedByParking servicesProvidedByParking = new ServicesProvidedByParking();
        servicesProvidedByParking.setId(resultStatement.getInt("id"));
        servicesProvidedByParking.setFence(resultStatement.getBoolean("fence"));
        servicesProvidedByParking.setSecurityCameras(resultStatement.getBoolean("security_cameras"));
        servicesProvidedByParking.setWc(resultStatement.getBoolean("wc"));
        servicesProvidedByParking.setShower(resultStatement.getBoolean("shower"));
        servicesProvidedByParking.setGuardedParking(resultStatement.getBoolean("guarded_parking"));
        servicesProvidedByParking.setLighting(resultStatement.getBoolean("lighting"));
        servicesProvidedByParking.setElectricity(resultStatement.getBoolean("electricity"));
        servicesProvidedByParking.setWater(resultStatement.getBoolean("water"));
        servicesProvidedByParking.setGasStation(resultStatement.getBoolean("gas_station"));
        servicesProvidedByParking.setWifi(resultStatement.getBoolean("wifi"));
        servicesProvidedByParking.setLodging(resultStatement.getBoolean("lodging"));
        servicesProvidedByParking.setTruckService(resultStatement.getBoolean("truck_service"));
        servicesProvidedByParking.setTruckWash(resultStatement.getBoolean("truck_wash"));
        servicesProvidedByParking.setStore(resultStatement.getBoolean("store"));
        servicesProvidedByParking.setFood(resultStatement.getBoolean("food"));
        return servicesProvidedByParking;
    }

    public Integer numberOfServices() {
        Integer number = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(AMOUNT_ID);
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
        ServicesProvidedByParking servicesProvidedByParking = (ServicesProvidedByParking) obj;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setInt(1, servicesProvidedByParking.getId());
            preparedStatement.setBoolean(2, servicesProvidedByParking.getFence());
            preparedStatement.setBoolean(3, servicesProvidedByParking.getSecurityCameras());
            preparedStatement.setBoolean(4, servicesProvidedByParking.getWc());
            preparedStatement.setBoolean(5, servicesProvidedByParking.getShower());
            preparedStatement.setBoolean(6, servicesProvidedByParking.getGuardedParking());
            preparedStatement.setBoolean(7, servicesProvidedByParking.getLighting());
            preparedStatement.setBoolean(8, servicesProvidedByParking.getElectricity());
            preparedStatement.setBoolean(9, servicesProvidedByParking.getWater());
            preparedStatement.setBoolean(10, servicesProvidedByParking.getGasStation());
            preparedStatement.setBoolean(11, servicesProvidedByParking.getWifi());
            preparedStatement.setBoolean(12, servicesProvidedByParking.getLodging());
            preparedStatement.setBoolean(13, servicesProvidedByParking.getTruckService());
            preparedStatement.setBoolean(14, servicesProvidedByParking.getTruckWash());
            preparedStatement.setBoolean(15, servicesProvidedByParking.getStore());
            preparedStatement.setBoolean(16, servicesProvidedByParking.getFood());
            System.out.println(servicesProvidedByParking);
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
        ServicesProvidedByParking servicesProvidedByParking = (ServicesProvidedByParking) obj;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setBoolean(1, servicesProvidedByParking.getFence());
            preparedStatement.setBoolean(2, servicesProvidedByParking.getSecurityCameras());
            preparedStatement.setBoolean(3, servicesProvidedByParking.getWc());
            preparedStatement.setBoolean(4, servicesProvidedByParking.getShower());
            preparedStatement.setBoolean(5, servicesProvidedByParking.getGuardedParking());
            preparedStatement.setBoolean(6, servicesProvidedByParking.getLighting());
            preparedStatement.setBoolean(7, servicesProvidedByParking.getElectricity());
            preparedStatement.setBoolean(8, servicesProvidedByParking.getWater());
            preparedStatement.setBoolean(9, servicesProvidedByParking.getGasStation());
            preparedStatement.setBoolean(10, servicesProvidedByParking.getWifi());
            preparedStatement.setBoolean(11, servicesProvidedByParking.getLodging());
            preparedStatement.setBoolean(12, servicesProvidedByParking.getTruckService());
            preparedStatement.setBoolean(13, servicesProvidedByParking.getTruckWash());
            preparedStatement.setBoolean(14, servicesProvidedByParking.getStore());
            preparedStatement.setBoolean(15, servicesProvidedByParking.getFood());
            preparedStatement.setInt(16, id);
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
