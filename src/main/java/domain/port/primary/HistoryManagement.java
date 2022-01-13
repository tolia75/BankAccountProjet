package domain.port.primary;

import domain.model.Operation;

import java.util.List;

public interface HistoryManagement {

    Operation getLastOperationFromCustomerId(long customerId);

    List<Operation> getOperationsFromCustomerId(long customerId);
}
