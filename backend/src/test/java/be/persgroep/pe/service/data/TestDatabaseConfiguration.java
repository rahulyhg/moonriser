package be.persgroep.pe.service.data;

import be.persgroep.pe.service.config.data.JdbcDataConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by gheylen on 10/12/2014.
 */

@ComponentScan(basePackages = "be.persgroep.pe.service.data.dao")
@Configuration
@Import({JdbcDataConfiguration.class})
@PropertySource("classpath:config/db.properties")
public class TestDatabaseConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() throws SQLException {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.usr"));
        dataSource.setPassword(env.getProperty("db.pwd"));

        return dataSource;
    }
}
