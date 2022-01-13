package infra.adapter.primary;

import domain.exception.MetierException;
import domain.model.Operation;
import domain.port.primary.HistoryManagement;
import domain.port.primary.MoneyManagement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AppRestController {

    private HistoryManagement historyManagement;
    private MoneyManagement moneyManagement;

    public AppRestController(HistoryManagement historyManagement, MoneyManagement moneyManagement) {
        this.historyManagement = historyManagement;
        this.moneyManagement = moneyManagement;
    }

    @PutMapping("/deposit")
    public ResponseEntity<Void> depositMoney(@RequestParam long customerId, @RequestParam BigDecimal amount) {
        try {
            moneyManagement.depositMoney(customerId, amount);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MetierException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/withdraw")
    public ResponseEntity<Void> withdrawMoney(@RequestParam long customerId, @RequestParam BigDecimal amount) {
        try {
            moneyManagement.withdrawMoney(customerId, amount);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MetierException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/lastOperation/{customerId}")
    public ResponseEntity<Operation> getLastOperationFromCustomerId(@PathVariable long customerId) {
        try {
            Operation operation = historyManagement.getLastOperationFromCustomerId(customerId);
            return new ResponseEntity<>(operation, HttpStatus.OK);
        } catch (MetierException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/operations/{customerId}")
    public ResponseEntity<List<Operation>> getOperationsFromCustomerId(@PathVariable long customerId) {
        try {
            List<Operation> operations = historyManagement.getOperationsFromCustomerId(customerId);
            return new ResponseEntity<>(operations, HttpStatus.OK);
        } catch (MetierException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
