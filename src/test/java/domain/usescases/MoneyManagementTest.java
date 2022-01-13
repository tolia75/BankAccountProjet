package domain.usescases;

import domain.exception.SoldeInsuffisantException;
import domain.exception.MetierException;
import domain.port.primary.MoneyManagement;
import domain.port.secondary.AccountManagement;
import domain.usecases.MoneyManagementCustomerHandler;
import infra.adapter.secondary.AccountManagementInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyManagementTest {

    MoneyManagement moneyManagement;
    AccountManagement accountManagement;
    AccountFixtures accountFixtures;

    @BeforeEach
    void setUp() {
        accountFixtures = new AccountFixtures();
        accountManagement = new AccountManagementInMemory(accountFixtures.getCustomersWithAccount());
        moneyManagement = new MoneyManagementCustomerHandler(accountManagement);
    }

    @Test
    void shouldGetTenEurosIfAccountWasEmpty() {
        // Given
        assertEquals(accountFixtures.getAmountFromCustomer1(), getAmountFromCustomerId(1L));

        // When
        moneyManagement.depositMoney(1L, BigDecimal.TEN);

        // Assert
        assertEquals(BigDecimal.TEN, getAmountFromCustomerId(1L));
    }

    @Test
    void shouldGet10EurosIfAccountHad10EurosEnd10MoreIsDeposited() {
        // Given
        assertEquals(accountFixtures.getAmountFromCustomer2(), getAmountFromCustomerId(2L));

        // When
        moneyManagement.depositMoney(2L, BigDecimal.TEN);

        // Assert
        assertEquals(BigDecimal.valueOf(20), getAmountFromCustomerId(2L));
    }

    @Test
    void shouldGetCustomerDoesntExistIfNotPresent() {
        try {
            moneyManagement.depositMoney(5L, BigDecimal.TEN);
        } catch (MetierException e) {
            assertEquals("Le client avec id '5' nexiste pas", e.getMessage());
        }
    }

    @Test
    void shouldGetCustomerDoesntExistIfNotPresentForWithdrawingMoney() {
        try {
            moneyManagement.withdrawMoney(5L, BigDecimal.TEN);
        } catch (MetierException e) {
            assertEquals("Le client avec id '5' nexiste pas", e.getMessage());
        }
    }

    @Test
    void shouldLeave0EurosIfAccountHad20EurosAnd10Iswithdrawed() {
        // Given
        assertEquals(accountFixtures.getAmountFromCustomer2(), getAmountFromCustomerId(2L));

        // When
        moneyManagement.withdrawMoney(2L, BigDecimal.TEN);

        // Assert
        assertEquals(BigDecimal.ZERO, getAmountFromCustomerId(2L));
    }

    @Test
    void shouldThrowSoldeInsuffisantExceptionIfWithdrawIsSuperiorToAmountInAccount() {
        // Given
        assertEquals(accountFixtures.getAmountFromCustomer1(), getAmountFromCustomerId(1L));

        // When
        assertThrows(SoldeInsuffisantException.class, () -> {
            moneyManagement.withdrawMoney(1L, BigDecimal.TEN);
        });
    }

    private BigDecimal getAmountFromCustomerId(long customerId) {
        return accountManagement.getCustomerWithAccount(customerId).get().getAccount().getAmount();
    }
}
