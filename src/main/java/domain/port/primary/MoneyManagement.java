package domain.port.primary;

import java.math.BigDecimal;

public interface MoneyManagement {

    void depositMoney(long customerId, BigDecimal amount);

    void withdrawMoney(long customerId, BigDecimal amount);

}
