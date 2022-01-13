package domain.model;

import java.math.BigDecimal;

public class Account {

    private BigDecimal amount;

    public Account(AccountBuilder accountBuilder) {
        this.amount = accountBuilder.amount;
    }

    public BigDecimal getAmount() {
        return amount;
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
