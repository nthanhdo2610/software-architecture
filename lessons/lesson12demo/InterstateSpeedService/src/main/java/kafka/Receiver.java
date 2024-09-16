package kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    @KafkaListener(topics = {"cameratopic1", "cameratopic2"}, groupId = "speedGroup")
    public void processCameraRecords(SensorRecord record) {
        SpeedCalculator speedCalculator = new SpeedCalculator();
        speedCalculator.handleRecord(record);
    }

}