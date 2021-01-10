package domain;

import java.io.Serializable;

public class ServicesProvidedByParking implements Serializable {
    private boolean fence;
    private boolean security_cameras;
    private boolean wc;
    private boolean shower;
    private boolean guarded_parking;
    private boolean lighting;
    private boolean electricity;
    private boolean water;
    private boolean gas_station;
    private boolean wifi;
    private boolean lodging;
    private boolean truck_service;
    private boolean truck_wash;
    private boolean store;
    private boolean food;

    public ServicesProvidedByParking(boolean fence, boolean security_cameras, boolean wc, boolean shower, boolean guarded_parking,
                                     boolean lighting, boolean electricity, boolean water, boolean gas_station, boolean wifi,
                                     boolean lodging, boolean truck_service, boolean truck_wash, boolean store, boolean food) {
        this.fence = fence;
        this.security_cameras = security_cameras;
        this.wc = wc;
        this.shower = shower;
        this.guarded_parking = guarded_parking;
        this.lighting = lighting;
        this.electricity = electricity;
        this.water = water;
        this.gas_station = gas_station;
        this.wifi = wifi;
        this.lodging = lodging;
        this.truck_service = truck_service;
        this.truck_wash = truck_wash;
        this.store = store;
        this.food = food;
    }
}
