package domain.usecases;

import domain.exception.MetierException;
import domain.model.Customer;
import domain.port.primary.MoneyManagement;
import domain.port.secondary.AccountManagement;

import java.math.BigDecimal;
import java.util.Optional;

public class MoneyManagementCustomerHandler implements MoneyManagement {

    private AccountManagement accountManagement;

    public MoneyManagementCustomerHandler(AccountManagement accountManagement) {
        this.accountManagement = accountManagement;
    }

    public void depositMoney(long customerId, BigDecimal amountDeposited) {
        Optional<Customer> customer = getCustomerById(customerId);
        customer.map(Customer::getAccount).map(account -> account.depositAmount(amountDeposited));
        accountManagement.updateCustomerWithNewAmount(customer.get());
    }

    @Override
    public void withdrawMoney(long customerId, BigDecimal amountWithdrawed) {
        Optional<Customer> customer = getCustomerById(customerId);
        customer.map(Customer::getAccount).map(account -> account.withdrawAmount(amountWithdrawed, customerId));
        accountManagement.updateCustomerWithNewAmount(customer.get());
    }

    private Optional<Customer> getCustomerById(long customerId) {
        Optional<Customer> customer = accountManagement.getCustomerWithAccount(customerId);
        customer.orElseThrow(() -> new MetierException("Le client avec id '" + customerId + "' nexiste pas"));
        return customer;
    }
}
