package domain.usescases;

import domain.exception.MetierException;
import domain.model.Operation;
import domain.port.primary.HistoryManagement;
import domain.port.secondary.OperationManagement;
import domain.usecases.HistoryManagementHandler;
import infra.adapter.secondary.OperationManagementInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HistoryManagementTest {

    private HistoryManagement historyManagement;
    private AccountFixtures accountFixtures;
    private HistoryOperationFixtures historyOperationFixtures;
    private OperationManagement operationManagement;

    @BeforeEach
    void setUp() {
        accountFixtures = new AccountFixtures();
        historyOperationFixtures = new HistoryOperationFixtures(accountFixtures);
        operationManagement = new OperationManagementInMemory(historyOperationFixtures.getOperations());
        historyManagement = new HistoryManagementHandler(operationManagement);
    }

    @Test
    void shouldGetHistoryOfTheLastOperation() {
        // When
        Operation operation = historyManagement.getLastOperationFromCustomerId(accountFixtures.getCustomer1().getId());

        // Assert
        assertEquals(historyOperationFixtures.getOperation3(), operation);
    }

    @Test
    void shouldNotGetLastOperationBecauseOfBadCustomerId() {
        // When
        try{
            historyManagement.getLastOperationFromCustomerId(accountFixtures.getCustomer2().getId());
        } catch (MetierException e) {
            assertEquals("Le client avec id '2' nexiste pas", e.getMessage());
        }
    }

    @Test
    void shouldGetOperationsFromCustomerId() {
        // When
        List<Operation> operations = historyManagement.getOperationsFromCustomerId(accountFixtures.getCustomer1().getId());

        // Assert
        assertEquals(historyOperationFixtures.getOperationsFromCustomer1().size(), operations.size());
        assertEquals(historyOperationFixtures.getOperation1(), operations.get(0));
        assertEquals(historyOperationFixtures.getOperation3(), operations.get(1));

    }
}
