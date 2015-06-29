package be.persgroep.pe.service.config;

import be.persgroep.pe.service.health.DioContentHealthIndicator;
import org.springframework.boot.actuate.health.DataSourceHealthIndicator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by gheylen on 11/12/2014.
 */
@Configuration
public class HealthConfiguration {
    @Bean
    public HealthIndicator dbHealthIndicator(final DataSource datasource) {
        return new DataSourceHealthIndicator(datasource);
    }

    @Bean
    public DioContentHealthIndicator dioContentHealthIndicator() {
        return new DioContentHealthIndicator();
    }
}
