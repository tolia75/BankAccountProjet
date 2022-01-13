package domain.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Operation {

    private long id;
    private Date date;
    private Customer customer;
    private BigDecimal amount;

    public Operation(Operation.OperationBuilder operationBuilder) {
        this.id = operationBuilder.id;
        this.date = operationBuilder.date;
        this.customer = operationBuilder.customer;
        this.amount = operationBuilder.amount;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return this.date;
    }

    public long getCustomerId() {
        return this.customer.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return id == operation.id &&
                Objects.equals(date, operation.date) &&
                Objects.equals(customer, operation.customer) &&
                Objects.equals(amount, operation.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, customer, amount);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", date=" + date +
                ", customer=" + customer +
                ", amount=" + amount +
                '}';
    }

    public static class OperationBuilder {
        private long id;
        private Date date;
        private Customer customer;
        private BigDecimal amount;

        public OperationBuilder id(long id){
            this.id = id;
            return this;
        }

        public OperationBuilder date(Date date){
            this.date = date;
            return this;
        }

        public OperationBuilder customer(Customer customer){
            this.customer = customer;
            return this;
        }

        public OperationBuilder amount(BigDecimal amount){
            this.amount = amount;
            return this;
        }

        public Operation build() {
            return new Operation(this);
        }
    }
}
