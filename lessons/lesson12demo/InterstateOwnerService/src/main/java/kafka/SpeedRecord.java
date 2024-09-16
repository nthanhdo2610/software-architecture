package kafka;

public class SpeedRecord {
    private String licencePlate;
    private int speed;

    public SpeedRecord(String licencePlate, int speed) {
        this.licencePlate = licencePlate;
        this.speed = speed;
    }

    public SpeedRecord() {
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
