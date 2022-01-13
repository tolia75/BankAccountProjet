package infra.adapter.secondary;

import domain.model.Customer;
import domain.port.secondary.AccountManagement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountManagementInMemory implements AccountManagement {

    List<Customer> customers;

    public AccountManagementInMemory(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public Optional<Customer> getCustomerWithAccount(long customerId) {
        return customers.stream()
                .filter(customer -> customer.getId() == customerId)
                .findAny();
    }

    @Override
    public void updateCustomerWithNewAmount(Customer customerWithNewAmount) {
        customers = customers.stream()
                .map(customer -> customer.getId() == customerWithNewAmount.getId() ? customerWithNewAmount : customer)
                .collect(Collectors.toList());
    }
}
