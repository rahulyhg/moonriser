package be.persgroep.pe.service.config.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by gheylen on 9/12/2014.
 */

@ComponentScan(basePackages = "be.persgroep.pe.service.data.dao")
@Configuration
public class DatabaseConfiguration {
    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() throws SQLException {
        final String driver = this.environment.getProperty("db.driver");
        final String url = this.environment.getProperty("db.url");
        final String username = this.environment.getProperty("db.usr");
        final String password = this.environment.getProperty("db.pwd");

        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
