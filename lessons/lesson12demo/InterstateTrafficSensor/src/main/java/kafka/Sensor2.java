package kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class Sensor2 {

    @Autowired
    private Sender sender;

    int car1CameraId = 1;
    int licencePlateNumber1 = 1000;

    int car2CameraId = 1;
    int licencePlateNumber2 = 1000;

    int car3CameraId = 1;
    int licencePlateNumber3 = 1000;

    int car4CameraId = 1;
    int licencePlateNumber4 = 1000;

    int car5CameraId = 1;
    int licencePlateNumber5 = 1000;

    int car6CameraId = 1;
    int licencePlateNumber6 = 1000;

    int car7CameraId = 1;
    int licencePlateNumber7 = 1000;

    int car8CameraId = 1;
    int licencePlateNumber8 = 1000;

    int car9CameraId = 1;
    int licencePlateNumber9 = 1000;

    int car10CameraId = 1;
    int licencePlateNumber10 = 1000;


    @Scheduled(fixedRate = 25800)
    public void car1() {
        String cameratopic = "cameratopic" + car1CameraId;

        String licencePlate = "AS" + licencePlateNumber1;
        SensorRecord sensorRecord = new SensorRecord(licencePlate, LocalTime.now().getMinute(), LocalTime.now().getSecond(), car1CameraId);
        sender.send(cameratopic, sensorRecord);
        System.out.println("sending to topic:" + cameratopic + "  " + sensorRecord.cameraId + " " + sensorRecord.licencePlate);
        car1CameraId++;
        if (car1CameraId == 3) {
            car1CameraId = 1;
            licencePlateNumber1++;
        }
    }

    @Scheduled(fixedRate = 23900)
    public void car3() {
        String cameratopic = "cameratopic" + car3CameraId;

        String licencePlate = "BS" + licencePlateNumber3;
        SensorRecord sensorRecord = new SensorRecord(licencePlate, LocalTime.now().getMinute(), LocalTime.now().getSecond(), car3CameraId);
        sender.send(cameratopic, sensorRecord);
        System.out.println("sending to topic:" + cameratopic + "  " + sensorRecord.cameraId + " " + sensorRecord.licencePlate);
        car3CameraId++;
        if (car3CameraId == 3) {
            car3CameraId = 1;
            licencePlateNumber3++;
        }
    }

    @Scheduled(fixedRate = 26100)
    public void car4() {
        String cameratopic = "cameratopic" + car4CameraId;

        String licencePlate = "CS" + licencePlateNumber4;
        SensorRecord sensorRecord = new SensorRecord(licencePlate, LocalTime.now().getMinute(), LocalTime.now().getSecond(), car4CameraId);
        sender.send(cameratopic, sensorRecord);
        System.out.println("sending to topic:" + cameratopic + "  " + sensorRecord.cameraId + " " + sensorRecord.licencePlate);
        car4CameraId++;
        if (car4CameraId == 3) {
            car4CameraId = 1;
            licencePlateNumber4++;
        }
    }


    @Scheduled(fixedRate = 22900)
    public void car5() {
        String cameratopic = "cameratopic" + car5CameraId;

        String licencePlate = "ES" + licencePlateNumber5;
        SensorRecord sensorRecord = new SensorRecord(licencePlate, LocalTime.now().getMinute(), LocalTime.now().getSecond(), car5CameraId);
        sender.send(cameratopic, sensorRecord);
        System.out.println("sending to topic:" + cameratopic + "  " + sensorRecord.cameraId + " " + sensorRecord.licencePlate);
        car5CameraId++;
        if (car5CameraId == 3) {
            car5CameraId = 1;
            licencePlateNumber5++;
        }
    }


    @Scheduled(fixedRate = 26700)
    public void car6() {
        String cameratopic = "cameratopic" + car6CameraId;

        String licencePlate = "FS" + licencePlateNumber6;
        SensorRecord sensorRecord = new SensorRecord(licencePlate, LocalTime.now().getMinute(), LocalTime.now().getSecond(), car6CameraId);
        sender.send(cameratopic, sensorRecord);
        System.out.println("sending to topic:" + cameratopic + "  " + sensorRecord.cameraId + " " + sensorRecord.licencePlate);
        car6CameraId++;
        if (car6CameraId == 3) {
            car6CameraId = 1;
            licencePlateNumber6++;
        }
    }

    @Scheduled(fixedRate = 25900)
    public void car7() {
        String cameratopic = "cameratopic" + car7CameraId;

        String licencePlate = "GS" + licencePlateNumber7;
        SensorRecord sensorRecord = new SensorRecord(licencePlate, LocalTime.now().getMinute(), LocalTime.now().getSecond(), car7CameraId);
        sender.send(cameratopic, sensorRecord);
        System.out.println("sending to topic:" + cameratopic + "  " + sensorRecord.cameraId + " " + sensorRecord.licencePlate);
        car7CameraId++;
        if (car7CameraId == 3) {
            car7CameraId = 1;
            licencePlateNumber7++;
        }
    }

    @Scheduled(fixedRate = 24200)
    public void car8() {
        String cameratopic = "cameratopic" + car8CameraId;

        String licencePlate = "FBS" + licencePlateNumber8;
        SensorRecord sensorRecord = new SensorRecord(licencePlate, LocalTime.now().getMinute(), LocalTime.now().getSecond(), car8CameraId);
        sender.send(cameratopic, sensorRecord);
        System.out.println("sending to topic:" + cameratopic + "  " + sensorRecord.cameraId + " " + sensorRecord.licencePlate);
        car8CameraId++;
        if (car8CameraId == 3) {
            car8CameraId = 1;
            licencePlateNumber8++;
        }
    }

    @Scheduled(fixedRate = 23200)
    public void car9() {
        String cameratopic = "cameratopic" + car9CameraId;

        String licencePlate = "FAS" + licencePlateNumber9;
        SensorRecord sensorRecord = new SensorRecord(licencePlate, LocalTime.now().getMinute(), LocalTime.now().getSecond(), car9CameraId);
        sender.send(cameratopic, sensorRecord);
        System.out.println("sending to topic:" + cameratopic + "  " + sensorRecord.cameraId + " " + sensorRecord.licencePlate);
        car9CameraId++;
        if (car9CameraId == 3) {
            car9CameraId = 1;
            licencePlateNumber9++;
        }
    }

    @Scheduled(fixedRate = 28500)
    public void car10() {
        String cameratopic = "cameratopic" + car10CameraId;

        String licencePlate = "FGS" + licencePlateNumber10;
        SensorRecord sensorRecord = new SensorRecord(licencePlate, LocalTime.now().getMinute(), LocalTime.now().getSecond(), car10CameraId);
        sender.send(cameratopic, sensorRecord);
        System.out.println("sending to topic:" + cameratopic + "  " + sensorRecord.cameraId + " " + sensorRecord.licencePlate);
        car10CameraId++;
        if (car10CameraId == 3) {
            car10CameraId = 1;
            licencePlateNumber10++;
        }
    }

}
