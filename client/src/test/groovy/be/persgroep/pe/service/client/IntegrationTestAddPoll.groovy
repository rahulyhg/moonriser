package be.persgroep.pe.service.client

import be.persgroep.pe.service.domain.container.Container
import be.persgroep.pe.service.domain.component.article.Article
import be.persgroep.pe.service.domain.embeddable.EmbeddedComponent
import be.persgroep.pe.service.domain.component.article.ArticleCultureType
import be.persgroep.pe.service.domain.component.article.ArticleLabelType
import be.persgroep.pe.service.domain.component.article.ArticleType
import be.persgroep.pe.service.domain.component.ComponentEmoVotesState
import be.persgroep.pe.service.domain.component.ComponentRatingState
import be.persgroep.pe.service.domain.component.ComponentReactionState
import be.persgroep.pe.service.domain.component.ComponentState
import be.persgroep.pe.service.domain.embeddable.EmbeddableAlignment
import be.persgroep.pe.service.domain.wrapper.PostArticleRequest
import be.persgroep.pe.service.domain.wrapper.SaveArticleResponse
import org.joda.time.DateTime
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import spock.lang.Specification

/**
 * Created by wim van den brande on 15/01/2015.
 */
class IntegrationTestAddPoll extends Specification {

    def "Add Article with pointer to existing Poll"() {
        given:
        MoonriserServiceClientFactory factory = MoonriserServiceClientFactory.createMoonriserServiceClientFactory(Country.BE);
        MoonriserServiceClient client = factory.getClient(MoonriserEnvironment.LOCAL);

        Article article = new Article();
        article.setArticleType(ArticleType.DEFAULT);
        article.setCultureRefId(10);

        article.setCultureType(ArticleCultureType.EVENT);
        article.setIntroduction("This is the introduction");
        article.setLabelType(ArticleLabelType.BACKGROUND);
        article.setSubtitle("Subtitle");
        article.setTitle("Title");
        article.setCreationDateTime((new DateTime()));
        article.setCreationUserUuid((long) 999999);
        article.setModificationDateTime(new DateTime());
        article.setModificationUserUuid((long) 999999);
        article.setEmoVotesState(ComponentEmoVotesState.DISABLED);
        article.setLocale(new Locale("nl_BE"));
        article.setOriginatingKey("dinero:11");
        article.setPublicationStartDateTime(new DateTime().minusDays(1));
        article.setPublicationEndDateTime(new DateTime().plusDays(10));
        article.setRatingState(ComponentRatingState.DISABLED);
        article.setReactionState(ComponentReactionState.DISABLED);
        article.setState(ComponentState.APPROVED_FOR_PUBLICATION);
        article.setPrintPageNumber(5);

        Container container = new Container();
        container.setTitle("This is TITLE of paragraph 3");
        container.setParagraphText("This is a first paragraph with ranking 3");

        // wim : poll to be added to first paragraph
        EmbeddedComponent embeddedComponent = new EmbeddedComponent();
        embeddedComponent.setComponentId(2130395); // id of an already existing component (being an existing poll )
        embeddedComponent.setAlignment(EmbeddableAlignment.BOTTOM_CENTER);
        embeddedComponent.setRanking(2); // the embeddedComponent will be ranked second

        container.addEmbeddable(embeddedComponent);

        // add first container | paragraph
        article.addContainer(container);

        Integer masterNode = 2668; //Belgisch Voetbal
        PostArticleRequest request = new PostArticleRequest(article, masterNode);

        request.getNodeIds().add(masterNode);
        request.getNodeIds().add(2530); //Voetbal

        request.getNavigationIds().add(1);

        when:
        SaveArticleResponse response = client.Articles.saveArticle(request);
        System.out.println("Article id:" + response.getArticleId());

        then:
        true
    }

    def "Add Article with pointer to unknown Poll"() {
        given:
        MoonriserServiceClientFactory factory = MoonriserServiceClientFactory.createMoonriserServiceClientFactory(Country.BE);
        MoonriserServiceClient client = factory.getClient(MoonriserEnvironment.LOCAL);

        Article article = new Article();
        article.setArticleType(ArticleType.DEFAULT);
        article.setCultureRefId(10);

        article.setCultureType(ArticleCultureType.EVENT);
        article.setIntroduction("This is the introduction");
        article.setLabelType(ArticleLabelType.BACKGROUND);
        article.setSubtitle("Subtitle");
        article.setTitle("Title");
        article.setCreationDateTime((new DateTime()));
        article.setCreationUserUuid((long) 999999);
        article.setModificationDateTime(new DateTime());
        article.setModificationUserUuid((long) 999999);
        article.setEmoVotesState(ComponentEmoVotesState.DISABLED);
        article.setLocale(new Locale("nl_BE"));
        article.setOriginatingKey("dinero:11");
        article.setPublicationStartDateTime(new DateTime().minusDays(1));
        article.setPublicationEndDateTime(new DateTime().plusDays(10));
        article.setRatingState(ComponentRatingState.DISABLED);
        article.setReactionState(ComponentReactionState.DISABLED);
        article.setState(ComponentState.APPROVED_FOR_PUBLICATION);
        article.setPrintPageNumber(5);

        Container container = new Container();
        container.setTitle("This is TITLE of paragraph 3");
        container.setParagraphText("This is a first paragraph with ranking 3");

        // wim : poll to be added to first paragraph
        EmbeddedComponent embeddedComponent = new EmbeddedComponent();
        embeddedComponent.setComponentId(2130395666); // id of an unknown component (being a poll)
        embeddedComponent.setAlignment(EmbeddableAlignment.BOTTOM_CENTER);
        embeddedComponent.setRanking(2); // the embeddedComponent will be ranked second

        container.addEmbeddable(embeddedComponent);

        // add first container | paragraph
        article.addContainer(container);

        Integer masterNode = 2668; //Belgisch Voetbal
        PostArticleRequest request = new PostArticleRequest(article, masterNode);

        when:
        SaveArticleResponse response = client.Articles.saveArticle(request);

        then:
        def e = thrown(HttpClientErrorException)
        e.statusCode == HttpStatus.BAD_REQUEST
    }

}