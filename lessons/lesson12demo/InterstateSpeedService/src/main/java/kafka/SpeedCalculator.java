package kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SpeedCalculator {
    @Autowired
    Sender sender;

    private Map<String, SensorRecord> recordstream = new HashMap<>();

    public void handleRecord(SensorRecord sensorRecord) {
        int speed;
        int time = 1000;
        if (sensorRecord.getCameraId() == 1) {
            recordstream.put(sensorRecord.getLicencePlate(), sensorRecord);
        }
        if (sensorRecord.getCameraId() == 2) {
            SensorRecord sensorRecord1 = recordstream.get(sensorRecord.getLicencePlate());
            if (sensorRecord1 != null) {
                if (sensorRecord1.getMinute() == sensorRecord.getMinute()) {
                    time = sensorRecord.getSecond() - sensorRecord1.getSecond();
                } else if ((sensorRecord.getMinute() - sensorRecord1.getMinute()) == 1) {
                    time = (sensorRecord.getSecond() + 60) - sensorRecord1.getSecond();
                }
                speed = (int) ((0.5 / time) * 3600);
                System.out.println("speed =" + speed);
                recordstream.remove(sensorRecord.getLicencePlate());
                if (speed > 72) {
                    System.out.println("speeding ********" + sensorRecord.getLicencePlate() + " = " + speed);
                    sender.send("tofasttopic", new SpeedRecord(sensorRecord.getLicencePlate(), speed));
                }
            }
        }
    }

}
