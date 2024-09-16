package kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FeeCalculatorService {

    @KafkaListener(topics = "ownerfeetopic", groupId = "feeGroup")
    public void calculateFee(FeeRecord record) {
        // Extract speed, license plate, and owner info from the data
        int speed = record.getSpeed();
        String licencePlate = record.getLicencePlate();
        String ownerInfo = record.getOwnerInfo();

        int fee = 0;
        if (speed > 90) {
            fee = 125;
        } else if (speed > 82) {
            fee = 80;
        } else if (speed > 77) {
            fee = 45;
        } else if (speed > 72) {
            fee = 25;
        }

        System.out.println("License plate: " + licencePlate + ", Owner: " + ownerInfo + ", Speed: " + speed + ", Fee: $" + fee);
    }
}
