package infra.adapter.secondary;

import domain.exception.MetierException;
import domain.model.Operation;
import domain.port.secondary.OperationManagement;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OperationManagementInMemory implements OperationManagement {

    private List<Operation> operations;

    public OperationManagementInMemory(List<Operation> operations) {
        this.operations = operations;
    }

    @Override
    public Operation getLastOperationFromCustomerId(long customerId) {
        List<Operation> operationsFromCustomer = getOperationsFromCustomerId(customerId);
        Date dateFromLastOperation = getDateFromLastOperation(operationsFromCustomer);

        return operationsFromCustomer.stream()
                .filter(operation -> operation.getDate() == dateFromLastOperation)
                .findFirst()
                .get();
    }

    private Date getDateFromLastOperation(List<Operation> operationsFromCustomer) {
        return operationsFromCustomer.stream()
                    .map(Operation::getDate)
                    .max(Date::compareTo)
                    .get();
    }

    public List<Operation> getOperationsFromCustomerId(long customerId) {
        List<Operation> operationsFromCustomer =  operations.stream()
                    .filter(operation -> operation.getCustomerId() == customerId)
                    .collect(Collectors.toList());

        if (operationsFromCustomer.isEmpty()) {
            throw new MetierException("Le client avec id '"+customerId+"' nexiste pas");
        }

        return operationsFromCustomer;
    }
}
