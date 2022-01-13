package configuration;

import domain.port.primary.HistoryManagement;
import domain.port.primary.MoneyManagement;
import domain.port.secondary.AccountManagement;
import domain.port.secondary.OperationManagement;
import domain.usecases.HistoryManagementHandler;
import domain.usecases.MoneyManagementCustomerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigurationApplication {

    private OperationManagement operationManagement;
    private AccountManagement accountManagement;

    public ConfigurationApplication(OperationManagement operationManagement, AccountManagement accountManagement) {
        this.operationManagement = operationManagement;
        this.accountManagement = accountManagement;
    }

    @Bean
    public HistoryManagement historyManagement() {return new HistoryManagementHandler(operationManagement);}

    @Bean
    public MoneyManagement moneyManagement() {return new MoneyManagementCustomerHandler(accountManagement);}
}
