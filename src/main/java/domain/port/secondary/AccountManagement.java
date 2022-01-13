package domain.port.secondary;

import java.math.BigDecimal;

public interface AccountManagement {
    BigDecimal addDepositMoneyToAccount(long customerId, BigDecimal amountOfMoneyToDeposit);
    BigDecimal getNewAmountByClient(long customerId);
}
