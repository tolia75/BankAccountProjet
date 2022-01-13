package infra.adapter.secondary;

import domain.exception.MetierException;
import domain.model.Account;
import domain.model.Customer;
import domain.port.secondary.AccountManagement;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class AccountManagementInMemory implements AccountManagement {

//    Map<Long, BigDecimal> clientsAccounts;
    List<Customer> customers;
//    public AccountManagementInMemory(Map<Long, BigDecimal> clientsAccounts) {
//        this.clientsAccounts = clientsAccounts;
//    }


    public AccountManagementInMemory(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public BigDecimal getNewAmountByClient(long customerId) {
        return customers.stream()
                .filter(customer -> customer.getId() == customerId)
                .findAny()
                .orElseThrow(() -> new MetierException("Le client avec id '\"+customerId+\"' nexiste pas"))
                .getAccount()
                .getAmount();
    }

    @Override
    public BigDecimal addDepositMoneyToAccount(long customerId, BigDecimal amountOfMoneyToDeposit) {
        Customer customerWanted = getCustomer(customerId);
        BigDecimal newAmount = getNewAmount(amountOfMoneyToDeposit, customerWanted);

        Account accountWithNewAmount = new Account.AccountBuilder()
                .amount(newAmount)
                .build();
        Customer customerWithNewAmount = new Customer.CustomerBuilder()
                .id(customerWanted.getId())
                .account(accountWithNewAmount)
                .build();

        customers.remove(customerWanted);
        customers.add(customerWithNewAmount);

        return newAmount;
    }

    private Customer getCustomer(long customerId) {
        return customers.stream().
                    filter(customer -> customer.getId() == customerId)
                    .findAny()
                    .orElseThrow(() -> new MetierException("Le client avec id '"+customerId+"' nexiste pas"));
    }

    private BigDecimal getNewAmount(BigDecimal amountOfMoneyToDeposit, Customer customerWanted) {
        BigDecimal previousAmount = customerWanted.getAccount().getAmount();
        return previousAmount.add(amountOfMoneyToDeposit);
    }
}
