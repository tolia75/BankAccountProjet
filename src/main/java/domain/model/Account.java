package domain.model;

import domain.exception.SoldeInsuffisantException;

import java.math.BigDecimal;

public class Account {

    private static final int ACTUAL_AMOUNT_INFERIOR_TO_WITHDRAWED_AMOUNT = 1;

    private BigDecimal amount;

    public Account(AccountBuilder accountBuilder) {
        this.amount = accountBuilder.amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal depositAmount(BigDecimal depositedAmount){
        this.amount = this.amount.add(depositedAmount);
        return this.amount;
    }

    public BigDecimal withdrawAmount(BigDecimal withdrawedAmount, long customerId) {
        if (isSoldeInsuffisant(withdrawedAmount, this.amount)) {
            throw new SoldeInsuffisantException("Le solde du client '"+customerId+"' est insuffisant");
        }
        this.amount = this.amount.subtract(withdrawedAmount);
        return this.amount;
    }

    private boolean isSoldeInsuffisant(BigDecimal amountWithdrawed, BigDecimal actualAmount) {
        return amountWithdrawed.compareTo(actualAmount) == ACTUAL_AMOUNT_INFERIOR_TO_WITHDRAWED_AMOUNT;
    }

    public static class AccountBuilder {

        private BigDecimal amount;

        public AccountBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Account build() {
            return new Account(this);
        }

    }
}
