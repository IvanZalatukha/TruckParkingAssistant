package domain;

public class Parking {
    private String name;
    private int spotsTotal;
    private int spotsCurrently;
    private double coordinateLatitude;
    private double coordinateLongitude;
    private ParkingServices parkingServices;
    private int id;
    private static int currentID;

    public Parking(String name, int spotsTotal, int spotsCurrently, double coordinateLatitude, double coordinateLongitude, ParkingServices parkingServices) {
        this.name = name;
        this.spotsTotal = spotsTotal;
        this.spotsCurrently = spotsCurrently;
        this.coordinateLatitude = coordinateLatitude;
        this.coordinateLongitude = coordinateLongitude;
        this.parkingServices = parkingServices;
        this.id = ++currentID;
    }

    public Parking() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpotsTotal() {
        return spotsTotal;
    }

    public void setSpotsTotal(int spotsTotal) {
        this.spotsTotal = spotsTotal;
    }

    public int getSpotsCurrently() {
        return spotsCurrently;
    }

    public void setSpotsCurrently(int spotsCurrently) {
        this.spotsCurrently = spotsCurrently;
    }

    public double getCoordinateLatitude() {
        return coordinateLatitude;
    }

    public void setCoordinateLatitude(double coordinateLatitude) {
        this.coordinateLatitude = coordinateLatitude;
    }

    public double getCoordinateLongitude() {
        return coordinateLongitude;
    }

    public void setCoordinateLongitude(double coordinateLongitude) {
        this.coordinateLongitude = coordinateLongitude;
    }

    public ParkingServices getParkingServices() {
        return parkingServices;
    }

    public void setParkingServices(ParkingServices parkingServices) {
        this.parkingServices = parkingServices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "name='" + name + '\'' +
                ", spotsTotal=" + spotsTotal +
                ", spotsCurrently=" + spotsCurrently +
                ", coordinateLatitude=" + coordinateLatitude +
                ", coordinateLongitude=" + coordinateLongitude +
                ", parkingServices=" + parkingServices +
                ", id=" + id +
                '}';
    }
}
