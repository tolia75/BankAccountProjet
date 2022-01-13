package domain.usescases;

import domain.model.Account;
import domain.model.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountFixtures {

    private Account account1 = new Account.AccountBuilder()
            .amount(BigDecimal.ZERO)
            .build();
    private Account account2 = new Account.AccountBuilder()
            .amount(BigDecimal.TEN)
            .build();

    private Customer customer1 = new Customer.CustomerBuilder()
            .id(1L)
            .account(account1)
            .build();
    private Customer customer2 = new  Customer.CustomerBuilder()
            .id(2L)
            .account(account2)
            .build();

    public List<Customer> getCustomersWithAccount () {
        List<Customer> customersWithAccount = new ArrayList<>();

        customersWithAccount.add(getCustomer1());
        customersWithAccount.add(getCustomer2());

        return customersWithAccount;
    }

    public Customer getCustomer1() {
        return customer1;
    }

    public Customer getCustomer2() {
        return customer2;
    }

    public BigDecimal getAmountFromCustomer1() {
        return getCustomer1().getAccount().getAmount();
    }

    public BigDecimal getAmountFromCustomer2() {
        return getCustomer2().getAccount().getAmount();
    }
}
