package by.zalatukha.tpa.entity;

import java.io.Serializable;

public class ServicesProvidedByParking implements Serializable {
    private boolean fence;
    private boolean security_cameras;
    private boolean wc;
    private boolean shower;
    private boolean guardedParking;
    private boolean lighting;
    private boolean electricity;
    private boolean water;
    private boolean gasStation;
    private boolean wifi;
    private boolean lodging;
    private boolean truckService;
    private boolean truckWash;
    private boolean store;
    private boolean food;
    private int id;
    private static int currentID;

    public ServicesProvidedByParking(boolean fence, boolean security_cameras, boolean wc, boolean shower, boolean guarded_parking,
                                     boolean lighting, boolean electricity, boolean water, boolean gas_station, boolean wifi,
                                     boolean lodging, boolean truck_service, boolean truck_wash, boolean store, boolean food) {
        this.fence = fence;
        this.security_cameras = security_cameras;
        this.wc = wc;
        this.shower = shower;
        this.guardedParking = guarded_parking;
        this.lighting = lighting;
        this.electricity = electricity;
        this.water = water;
        this.gasStation = gas_station;
        this.wifi = wifi;
        this.lodging = lodging;
        this.truckService = truck_service;
        this.truckWash = truck_wash;
        this.store = store;
        this.food = food;
        this.id = ++currentID;
    }

    public ServicesProvidedByParking() {
    }

    public boolean getFence() {
        return fence;
    }

    public void setFence(boolean fence) {
        this.fence = fence;
    }

    public boolean getSecurityCameras() {
        return security_cameras;
    }

    public void setSecurityCameras(boolean security_cameras) {
        this.security_cameras = security_cameras;
    }

    public boolean getWc() {
        return wc;
    }

    public void setWc(boolean wc) {
        this.wc = wc;
    }

    public boolean getShower() {
        return shower;
    }

    public void setShower(boolean shower) {
        this.shower = shower;
    }

    public boolean getGuardedParking() {
        return guardedParking;
    }

    public void setGuardedParking(boolean guarded_parking) {
        this.guardedParking = guarded_parking;
    }

    public boolean getLighting() {
        return lighting;
    }

    public void setLighting(boolean lighting) {
        this.lighting = lighting;
    }

    public boolean getElectricity() {
        return electricity;
    }

    public void setElectricity(boolean electricity) {
        this.electricity = electricity;
    }

    public boolean getWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean getGasStation() {
        return gasStation;
    }

    public void setGasStation(boolean gas_station) {
        this.gasStation = gas_station;
    }

    public boolean getWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean getLodging() {
        return lodging;
    }

    public void setLodging(boolean lodging) {
        this.lodging = lodging;
    }

    public boolean getTruckService() {
        return truckService;
    }

    public void setTruckService(boolean truck_service) {
        this.truckService = truck_service;
    }

    public boolean getTruckWash() {
        return truckWash;
    }

    public void setTruckWash(boolean truck_wash) {
        this.truckWash = truck_wash;
    }

    public boolean getStore() {
        return store;
    }

    public void setStore(boolean store) {
        this.store = store;
    }

    public boolean getFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ServicesProvidedByParking{" +
                "fence=" + fence +
                ", security_cameras=" + security_cameras +
                ", wc=" + wc +
                ", shower=" + shower +
                ", guardedParking=" + guardedParking +
                ", lighting=" + lighting +
                ", electricity=" + electricity +
                ", water=" + water +
                ", gasStation=" + gasStation +
                ", wifi=" + wifi +
                ", lodging=" + lodging +
                ", truckService=" + truckService +
                ", truckWash=" + truckWash +
                ", store=" + store +
                ", food=" + food +
                ", id=" + id +
                '}';
    }
}
