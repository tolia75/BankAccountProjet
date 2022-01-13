package domain.model;

public class Customer {

    private long id;
    private Account account;

    public Customer(CustomerBuilder customerBuilder) {
        this.id = customerBuilder.id;
        this.account = customerBuilder.account;
    }

    public long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", account=" + account +
                '}';
    }

    public static class CustomerBuilder {
        private long id;
        private Account account;

        public CustomerBuilder id(long id) {
            this.id = id;
            return this;
        }

        public CustomerBuilder account(Account account) {
            this.account = account;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
