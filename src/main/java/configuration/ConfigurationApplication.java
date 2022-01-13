package configuration;

import domain.port.secondary.AccountManagement;
import infra.adapter.secondary.AccountManagementInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class ConfigurationApplication {

    @Bean
    public AccountManagement accountManagement() {return new AccountManagementInMemory(new ArrayList<>());}
}
