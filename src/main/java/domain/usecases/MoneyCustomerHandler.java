package domain.usecases;

import domain.port.primary.SavingMoney;
import domain.port.secondary.AccountManagement;

import java.math.BigDecimal;

public class MoneyCustomerHandler implements SavingMoney {

    private AccountManagement accountManagement;

    public MoneyCustomerHandler(AccountManagement accountManagement) {
        this.accountManagement = accountManagement;
    }

    public BigDecimal depositMoney(long customerId, BigDecimal amount) {
        return accountManagement.addDepositMoneyToAccount(customerId, amount);
    }
}
