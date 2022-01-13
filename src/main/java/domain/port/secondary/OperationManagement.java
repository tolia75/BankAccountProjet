package domain.port.secondary;

import domain.model.Operation;

import java.util.List;

public interface OperationManagement {

    Operation getLastOperationFromCustomerId(long customerId);

    List<Operation> getOperationsFromCustomerId(long customerId);
}
