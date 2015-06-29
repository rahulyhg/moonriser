package be.persgroep.pe.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class MoonriserRestTemplate extends RestTemplate {
    public MoonriserRestTemplate() {
        super.getMessageConverters().add(new StringHttpMessageConverter());

        this.addJodaMessageConverters();
        this.addGuavaMessageConverters();
    }

    private void addJodaMessageConverters() {
        final ObjectMapper jodaObjectMapper = new ObjectMapper();
        jodaObjectMapper.registerModule(new JodaModule());
        jodaObjectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
        jodaObjectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverterJoda = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverterJoda.setObjectMapper(jodaObjectMapper);

        super.getMessageConverters().add(mappingJackson2HttpMessageConverterJoda);
    }

    private void addGuavaMessageConverters() {
        final ObjectMapper guavaObjectMapper = new ObjectMapper();
        guavaObjectMapper.registerModule(new GuavaModule());
        guavaObjectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverterGuava = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverterGuava.setObjectMapper(guavaObjectMapper);

        super.getMessageConverters().add(mappingJackson2HttpMessageConverterGuava);
    }
}
