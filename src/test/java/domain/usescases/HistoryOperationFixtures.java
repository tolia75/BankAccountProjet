package domain.usescases;

import domain.model.Operation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class HistoryOperationFixtures {

    private AccountFixtures accountFixtures;
    private Date date1 = new Date(111111111L);
    private Date date2 = new Date(222222222L);
    private Date date3 = new Date(333333333L);

    public HistoryOperationFixtures(AccountFixtures accountFixtures) {
        this.accountFixtures = accountFixtures;
    }

    public List<Operation> getOperations() {
        return List.of(getOperation1(), getOperation2(), getOperation3());
    }

    public Operation getOperation1() {
        return new Operation.OperationBuilder()
                .id(1)
                .date(date1)
                .customer(accountFixtures.getCustomer1())
                .amount(BigDecimal.TEN)
                .build();
    }

    public Operation getOperation2() {
        return new Operation.OperationBuilder()
                .id(2)
                .date(date2)
                .customer(accountFixtures.getCustomer2())
                .amount(BigDecimal.valueOf(5))
                .build();
    }

    public Operation getOperation3() {
        return new Operation.OperationBuilder()
                .id(3)
                .date(date3)
                .customer(accountFixtures.getCustomer1())
                .amount(BigDecimal.valueOf(5))
                .build();
    }

    public List<Operation> getOperationsFromCustomer1() {
        return List.of(getOperation1(), getOperation3());
    }
}
