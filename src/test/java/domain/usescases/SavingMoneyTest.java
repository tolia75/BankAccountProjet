package domain.usescases;

import domain.exception.MetierException;
import domain.port.primary.SavingMoney;
import domain.port.secondary.AccountManagement;
import domain.usecases.MoneyCustomerHandler;
import infra.adapter.secondary.AccountManagementInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SavingMoneyTest {

    SavingMoney savingMoney;
    AccountManagement accountManagement;

    @BeforeEach
    void setUp() {
        accountManagement = new AccountManagementInMemory(AccountFixtures.getCustomersWithAccount());
        savingMoney = new MoneyCustomerHandler(accountManagement);
    }

    @Test
    void shouldGetTenEurosIfAccountWasEmpty() {
        // Given
        assertEquals(accountManagement.getNewAmountByClient(1L), BigDecimal.ZERO);

        // When
        BigDecimal newAmount = savingMoney.depositMoney(1L, BigDecimal.TEN);

        // Assert
        assertEquals(BigDecimal.TEN, accountManagement.getNewAmountByClient(1L));
        assertEquals(BigDecimal.TEN, newAmount);
    }

    @Test
    void shouldGetTenEurosIfAccountHad10EurosEnd10MoreIsDeposited() {
        // Given
        assertEquals(accountManagement.getNewAmountByClient(2L), BigDecimal.TEN);

        // When
        BigDecimal newAmount = savingMoney.depositMoney(2L, BigDecimal.TEN);

        // Assert
        assertEquals(BigDecimal.valueOf(20), accountManagement.getNewAmountByClient(2L));
        assertEquals(BigDecimal.valueOf(20), newAmount);

    }

    @Test
    void shouldGetCustomerDoesntExistIfNotPresent() {
        try {
            savingMoney.depositMoney(5L, BigDecimal.TEN);
        } catch (MetierException e) {
            assertEquals("Le client avec id '5' nexiste pas", e.getMessage());
        }
    }
}
