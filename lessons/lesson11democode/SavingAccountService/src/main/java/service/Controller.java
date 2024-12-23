package service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/saving-account")
public class Controller {

    private Map<Long, Double> accounts = new HashMap<>();

    @PostMapping("/create")
    public String createSavingAccount(@RequestParam Long accountId) {
        accounts.put(accountId, 0.0);
        return "Saving account created with ID: " + accountId;
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam Long accountId, @RequestParam Double amount) {
        accounts.put(accountId, accounts.get(accountId) + amount);
        return "Deposited " + amount + " to saving account " + accountId;
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam Long accountId, @RequestParam Double amount) {
        Double currentBalance = accounts.get(accountId);
        if (currentBalance < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        accounts.put(accountId, currentBalance - amount);
        return "Withdrew " + amount + " from saving account " + accountId;
    }

    @GetMapping("/balance")
    public Double getBalance(@RequestParam Long accountId) {
        return accounts.get(accountId);
    }
}