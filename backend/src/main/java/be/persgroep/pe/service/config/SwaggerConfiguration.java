package be.persgroep.pe.service.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

/**
 * Created by jlust on 5/12/14.
 */
@Configuration
@EnableSwagger
public class SwaggerConfiguration {
    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .directModelSubstitute(DateTime.class, String.class)
                .directModelSubstitute(Locale.class, String.class)
                .includePatterns(".*articles.*");
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "PE Moonriser Service",
                "Preparing the glorious demise of PE",
                "",
                "Jurgen.Lust@Persgroep.be",
                "De Persgroep",
                "http://www.persgroep.be"
        );
        return apiInfo;
    }
}
