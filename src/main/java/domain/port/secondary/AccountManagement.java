package domain.port.secondary;

import domain.model.Customer;

import java.util.Optional;

public interface AccountManagement {

    void updateCustomerWithNewAmount(Customer customerWithNewAmount);

    Optional<Customer> getCustomerWithAccount(long customerId);
}
