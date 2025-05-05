package com.hexagonal.buckpal;

import com.hexagonal.buckpal.application.domain.model.Money;
import com.hexagonal.buckpal.application.domain.service.MoneyTransferProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({BuckPalConfigurationProperties.class})
public class BuckPalConfiguration {

    @Bean
    public MoneyTransferProperties moneyTransferProperties(BuckPalConfigurationProperties buckPalConfigurationProperties) {
        return new MoneyTransferProperties(Money.of(buckPalConfigurationProperties.getTransferThreshold()));
    }
}
