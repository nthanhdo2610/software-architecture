package kafka;

public class FeeRecord {
    private int speed;
    private String licencePlate;
    private String ownerInfo;

    public FeeRecord(int speed, String licencePlate, String ownerInfo) {
        this.speed = speed;
        this.licencePlate = licencePlate;
        this.ownerInfo = ownerInfo;
    }

    public FeeRecord() {
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getOwnerInfo() {
        return ownerInfo;
    }

    public void setOwnerInfo(String ownerInfo) {
        this.ownerInfo = ownerInfo;
    }
}
