package domain.usecases;

import domain.model.Operation;
import domain.port.primary.HistoryManagement;
import domain.port.secondary.OperationManagement;

import java.util.List;

public class HistoryManagementHandler implements HistoryManagement {

    private OperationManagement operationManagement;

    public HistoryManagementHandler(OperationManagement operationManagement) {
        this.operationManagement = operationManagement;
    }

    @Override
    public Operation getLastOperationFromCustomerId(long customerId) {
        return operationManagement.getLastOperationFromCustomerId(customerId);
    }

    @Override
    public List<Operation> getOperationsFromCustomerId(long customerId) {
        return operationManagement.getOperationsFromCustomerId(customerId);
    }
}
