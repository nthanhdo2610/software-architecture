package kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    @Autowired
    Sender sender;

    @KafkaListener(topics = "tofasttopic", groupId = "ownerGroup")
    public void processOverSpeedCars(SpeedRecord record) {
        // Lookup owner information based on the license plate
        String ownerInfo = lookupOwner(record.getLicencePlate());
        System.out.println("License plate: " + record.getLicencePlate() + ", Owner Info: " + ownerInfo);

        // Send data to FeeCalculatorService
        sender.send("ownerfeetopic", new FeeRecord(record.getSpeed(), record.getLicencePlate(), ownerInfo));
    }

    private String lookupOwner(String licencePlate) {
        // Mock implementation or call to an external service/database
        return "Owner of " + licencePlate;
    }

}