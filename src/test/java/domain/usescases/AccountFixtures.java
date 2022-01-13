package domain.usescases;

import domain.model.Account;
import domain.model.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountFixtures {

    private static Account account1 = new Account.AccountBuilder()
            .amount(BigDecimal.ZERO)
            .build();
    private static Account account2 = new Account.AccountBuilder()
            .amount(BigDecimal.TEN)
            .build();
    private static Account account3 = new Account.AccountBuilder()
            .amount(BigDecimal.TEN)
            .build();
    private static Account account4 = new Account.AccountBuilder()
            .amount(BigDecimal.TEN)
            .build();

    private static Customer customer1 = new Customer.CustomerBuilder()
            .id(1L)
            .account(account1)
            .build();
    private static Customer customer2 = new  Customer.CustomerBuilder()
            .id(2L)
            .account(account2)
            .build();
    private static Customer customer3 = new Customer.CustomerBuilder()
            .id(3L)
            .account(account3)
            .build();
    private static Customer customer4 = new Customer.CustomerBuilder()
            .id(4L)
            .account(account4)
            .build();

    public static List<Customer> getCustomersWithAccount () {
        List<Customer> customersWithAccount = new ArrayList<>();

        customersWithAccount.add(getCustomer1());
        customersWithAccount.add(getCustomer2());
        customersWithAccount.add(getCustomer3());
        customersWithAccount.add(getCustomer4());

        return customersWithAccount;
    }

    public static Customer getCustomer1() {
        return customer1;
    }

    public static Customer getCustomer2() {
        return customer2;
    }

    public static Customer getCustomer3() {
        return customer3;
    }

    public static Customer getCustomer4() {
        return customer4;
    }
}
