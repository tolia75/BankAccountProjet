package domain.port.primary;

import java.math.BigDecimal;

public interface SavingMoney {

    BigDecimal depositMoney(long customerId, BigDecimal amount);
}
