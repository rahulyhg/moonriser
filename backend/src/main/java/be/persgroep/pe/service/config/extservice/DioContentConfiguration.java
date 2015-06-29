package be.persgroep.pe.service.config.extservice;

import be.persgroep.red.diocontent.api.client.DioContentClient;
import be.persgroep.red.diocontent.webservice.client.DefaultRestDioContentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by gheylen on 12/01/2015.
 */
@Configuration
public class DioContentConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public DioContentClient getDioContentClient() {
        final String url = this.env.getProperty("diocontent.url");
        final String user = this.env.getProperty("diocontent.user");
        final String password = this.env.getProperty("diocontent.password");

        return new DefaultRestDioContentClient(url, user, password);
    }
}
