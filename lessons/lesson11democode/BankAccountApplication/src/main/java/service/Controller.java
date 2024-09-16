package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/transfer")
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/from-checking-to-saving")
    @Transactional
    public String transfer(@RequestParam Long checkingAccountId, @RequestParam Long savingAccountId, @RequestParam Double amount) {
        try {
            // Withdraw from checking account
            restTemplate.postForObject("http://localhost:8081/checking-account/withdraw?accountId=" + checkingAccountId + "&amount=" + amount, null, String.class);

            // Simulate error (uncomment to test rollback)
            // if (true) throw new RuntimeException("Simulated error during transfer");

            // Deposit to saving account
            restTemplate.postForObject("http://localhost:8082/saving-account/deposit?accountId=" + savingAccountId + "&amount=" + amount, null, String.class);

        } catch (Exception e) {
            // Rollback transaction if any error occurs
            throw new RuntimeException("Transfer failed. Transaction rolled back.", e);
        }

        return "Transfer successful!";
    }
}
