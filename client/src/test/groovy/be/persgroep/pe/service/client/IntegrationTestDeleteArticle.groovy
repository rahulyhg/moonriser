package be.persgroep.pe.service.client

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import spock.lang.Specification

/**
 * Created by gheylen on 15/01/2015.
 */
class IntegrationTestDeleteArticle extends Specification {

    def "Delete Known Article Test"() {
        given:
            MoonriserServiceClientFactory factory = MoonriserServiceClientFactory.createMoonriserServiceClientFactory(Country.BE);
            MoonriserServiceClient client = factory.getClient(MoonriserEnvironment.LOCAL);

        when:
            client.Articles.deleteArticle(2134932);

        then:
            true
    }

    def "Delete Unknown Article Test"() {
        given:
        MoonriserServiceClientFactory factory = MoonriserServiceClientFactory.createMoonriserServiceClientFactory(Country.BE);
        MoonriserServiceClient client = factory.getClient(MoonriserEnvironment.LOCAL);

        when:
        client.Articles.deleteArticle(-1);
        // client.Articles.deleteArticle(2134932);

        then:
        def e = thrown(HttpClientErrorException)
        e.statusCode == HttpStatus.NOT_FOUND
    }

}