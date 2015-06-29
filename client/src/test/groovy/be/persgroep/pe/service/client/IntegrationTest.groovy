package be.persgroep.pe.service.client

import be.persgroep.pe.service.domain.Node
import be.persgroep.pe.service.domain.component.ComponentEmoVotesState
import be.persgroep.pe.service.domain.component.ComponentRatingState
import be.persgroep.pe.service.domain.component.ComponentReactionState
import be.persgroep.pe.service.domain.component.ComponentState
import be.persgroep.pe.service.domain.component.ContainerType
import be.persgroep.pe.service.domain.component.article.Article
import be.persgroep.pe.service.domain.component.article.ArticleCultureType
import be.persgroep.pe.service.domain.component.article.ArticleLabelType
import be.persgroep.pe.service.domain.component.article.ArticleType
import be.persgroep.pe.service.domain.container.MediaContainer
import be.persgroep.pe.service.domain.embeddable.EmbeddableAlignment
import be.persgroep.pe.service.domain.embeddable.EmbeddedComponent
import be.persgroep.pe.service.domain.embeddable.FloatingText
import be.persgroep.pe.service.domain.embeddable.freehtml.FreeHTML
import be.persgroep.pe.service.domain.embeddable.grid.Grid
import be.persgroep.pe.service.domain.embeddable.grid.GridRow
import be.persgroep.pe.service.domain.embeddable.jsonrepresentable.Quote
import be.persgroep.pe.service.domain.embeddable.jsonrepresentable.SpotifyUri
import be.persgroep.pe.service.domain.embeddable.jsonrepresentable.Tweet
import be.persgroep.pe.service.domain.embeddable.jsonrepresentable.TwitterWidget
import be.persgroep.pe.service.domain.embeddable.photo.Photo
import be.persgroep.pe.service.domain.wrapper.PostArticleRequest
import be.persgroep.pe.service.domain.wrapper.SaveArticleResponse
import be.persgroep.pe.service.domain.wrapper.SearchNodesRequest
import be.persgroep.pe.service.domain.wrapper.SearchNodesResponse
import org.joda.time.DateTime
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import spock.lang.Specification

class IntegrationTest extends Specification {
    private static final String LOG_PREFIX_CLIENT = "Moonriser>>>Client>>>Test>>> ";

    def "Big integration test"() {
        when:
        MoonriserServiceClientFactory factory = MoonriserServiceClientFactory.createMoonriserServiceClientFactory(Country.BE);
        MoonriserServiceClient client = factory.getClient(MoonriserEnvironment.LOCAL);
        final SearchNodesRequest searchNodesRequest = new SearchNodesRequest();
        searchNodesRequest.setLocale(new Locale("nl", "NL"));
        searchNodesRequest.setQuery("politiek");
        final SearchNodesResponse searchNodesResponse = client.Nodes.searchNodes(searchNodesRequest);
        final List<Node> nodesFound = searchNodesResponse.getNodes();

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
        article.setRegional(false);

        // first paragraph
        // ---------------

        MediaContainer container = new MediaContainer();
        container.setTitle("This is TITLE of paragraph 3");
        container.setParagraphText("This is a first paragraph with ranking 3");
        // container.setType(ContainerType.MEDIA);

        // wim : existing POLL to be included in first paragraph
        EmbeddedComponent embeddedComponent = new EmbeddedComponent();
        embeddedComponent.setComponentId(2130395); // id of an already existing component (being an existing poll )
        embeddedComponent.setAlignment(EmbeddableAlignment.BOTTOM_CENTER);
        embeddedComponent.setRanking(2);

        container.addEmbeddable(embeddedComponent);

        // wim : existing URL to be included in first paragraph
        EmbeddedComponent embeddedComponent2 = new EmbeddedComponent();
        embeddedComponent2.setComponentId(2134092); // id of an already existing component (being an existing url )
        embeddedComponent2.setAlignment(EmbeddableAlignment.MIDDLE_RIGHT);
        embeddedComponent2.setRanking(3);

        container.addEmbeddable(embeddedComponent2);

        // wim : floating to be included in first paragraph
        FloatingText floating = new FloatingText();
        floating.setTitle("this is the title of the Floating");
        floating.setText("this is the text of the Floating");
        floating.setAlignment(EmbeddableAlignment.MIDDLE_RIGHT);
        floating.setRanking(4);

        container.addEmbeddable(floating);

        // wim : freeHTML to be included in first paragraph
        FreeHTML freeHTML = new FreeHTML();
        // TODO : to validate the HTML
        // freeHTML.setFreeHTMLTemplateType(FreeHTMLTemplateType.BELGA_BE);
        freeHTML.setText("<div class=\"dummy\"></div><a href=\"http://www.persgroep.be/\" onclick=\"measureClick('footer','depersgroep','image')\" target=\"_blank\">  <img src=\"http://ontw10g.static0.hln.be.persgroep.be/images/logos/logo_dpd.jpg\" alt=\"\" width=\"120\" height=\"43\" /></a>");
        freeHTML.setAlignment(EmbeddableAlignment.MIDDLE_RIGHT);
        freeHTML.setRanking(5);

        container.addEmbeddable(freeHTML);

        // wim : existing VIDEO to be included in first paragraph
        EmbeddedComponent embeddedComponent3 = new EmbeddedComponent();
        embeddedComponent3.setComponentId(2137031); // id of an already existing component (being an existing video )
        embeddedComponent3.setAlignment(EmbeddableAlignment.BOTTOM_CENTER);
        embeddedComponent3.setRanking(5);

        container.addEmbeddable(embeddedComponent3);

        // second paragraph
        // ----------------

        MediaContainer container2 = new MediaContainer();
        container2.setTitle("This is TITLE of paragraph 4");
        container2.setParagraphText("This is a second paragraph with ranking 4. This paragraph contains different and multiple embeddables (tweet, quote, spotifyUri ...)");
        container2.setType(ContainerType.MEDIA);

        // wim : quote to be added to second paragraph
        Quote quote = new Quote();
        quote.setQuoter("wim van den brande");
        quote.setText("Is het fuseren of fusioneren? Van Dale zegt dat het volledige synoniemen zijn dus duplicates in onze taal?");
        quote.setAlignment(EmbeddableAlignment.BOTTOM_CENTER);
        quote.setRanking(2); // the quote will be ranked second

        container2.addEmbeddable(quote);

        // wim : tweet to be added to second paragraph
        Tweet tweet = new Tweet();
        tweet.setText(".<a href=\\\"https://twitter.com/adobemerchant\\\">@adobemerchant</a> talks retail personalisation gap and how to know if you¿re in it: <a href=\\\"http://t.co/5yHe1c7jV6\\\">http://t.co/5yHe1c7jV6</a> <a href=\\\"http://t.co/cdfH7NhxKP\\\">pic.twitter.com/cdfH7NhxKP</a>");
        tweet.setAuthor("Marketing Cloud");
        tweet.setUsername("AdobeMktgCloud");
        tweet.setIdentifier("554616317592870913");
        tweet.setTimestamp(1421017200000l); // long
        tweet.setAlignment(EmbeddableAlignment.BOTTOM_CENTER);
        tweet.setRanking(3); // the tweet will be ranked third

        container2.addEmbeddable(tweet);

        // wim : TwitterWidget to be added to second paragraph
        TwitterWidget twitterWidget = new TwitterWidget();
        twitterWidget.setTwitterWidgetUserName("IctDynamic_BE");
        twitterWidget.setTwitterWidgetId("468331125304086528");
        twitterWidget.setAlignment(EmbeddableAlignment.BOTTOM_CENTER);
        twitterWidget.setRanking(4); // the TwitterWidget will be ranked forth

        container2.addEmbeddable(twitterWidget);

        // wim : SpotifyUri to be added to second paragraph
        SpotifyUri spotifyUri = new SpotifyUri();
        spotifyUri.setUri("spotify:album:3Y4WXd6VsyVhKiBUGOquGP");
        spotifyUri.setDisplayType(SpotifyUri.SpotifyUriDisplayType.SMALL);
        spotifyUri.setAlignment(EmbeddableAlignment.BOTTOM_CENTER);
        spotifyUri.setRanking(5); // the SpotifyUri will be ranked fifth

        container2.addEmbeddable(spotifyUri);

        // wim : Grid to be added to second paragraph
        ArrayList<GridRow> gridRowArrayList = new ArrayList<GridRow>();
        Grid grid = new Grid(gridRowArrayList);

        grid.setRow(0, 0, "this is the header");
        grid.setRow(2, 1, "row 1, column 1");
        grid.setRow(2, 2, "row 1, column 2");
        grid.setRow(2, 3, "row 1, column 3");
        grid.setRow(3, 1, "row 2, column 1");
        grid.setRow(3, 2, "row 2, column 2");
        grid.setRow(3, 3, "row 2, column 3");

        grid.setAlignment(EmbeddableAlignment.BOTTOM_CENTER);
        grid.setRanking(6); // the Grid will be ranked sixth

        container2.addEmbeddable(grid);

        MediaContainer container3 = new MediaContainer();
        container3.setTitle("Photo Container");
        container3.setParagraphText("Photo container TEXT");
        container3.setType(ContainerType.MEDIA);

        final Photo photo = new Photo();
        photo.setDioContentAssetId(2519398);
        photo.setRanking(2);
        photo.setAlignment(EmbeddableAlignment.MIDDLE_RIGHT);
        container3.addEmbeddable(photo);

        // add first and second container | paragraph
        article.addMediaContainer(container);
        article.addMediaContainer(container2);
        article.addMediaContainer(container3);

        Integer masterNode = 2668; //Belgisch Voetbal

        PostArticleRequest request = new PostArticleRequest(article, masterNode);
        request.getNodeIds().add(masterNode);
        request.getNodeIds().add(2530); //Voetbal

        request.getNavigationIds().add(1);
        //request.getNavigationIds().add(923);
        //request.getNavigationIds().add(9090);
        //request.getNavigationIds().add(949);
        //request.getNavigationIds().add(24001);
        //request.getNavigationIds().add(922);

        then:
        try {
            SaveArticleResponse response = client.Articles.saveArticle(request);
            System.out.println(LOG_PREFIX_CLIENT + " ... SUCCESS");
            System.out.println(LOG_PREFIX_CLIENT + "Article id:" + response.getArticleId());
        }
        catch (HttpClientErrorException e) {
            System.out.println(LOG_PREFIX_CLIENT + " ... CLIENT FAILURE");
            System.out.println(LOG_PREFIX_CLIENT + "message = " + e.message);
            System.out.println(LOG_PREFIX_CLIENT + "getStatusCode = " + e.getStatusCode());
            System.out.println(LOG_PREFIX_CLIENT + "getStatusText = " + e.getStatusText());
            System.out.println(LOG_PREFIX_CLIENT + "getResponseBodyAsString = " + e.getResponseBodyAsString());
        }
        catch (HttpServerErrorException e) {
            System.out.println(LOG_PREFIX_CLIENT + " ... SERVER FAILURE");
            System.out.println(LOG_PREFIX_CLIENT + "message = " + e.message);
            System.out.println(LOG_PREFIX_CLIENT + "getStatusCode = " + e.getStatusCode());
            System.out.println(LOG_PREFIX_CLIENT + "getStatusText = " + e.getStatusText());
            System.out.println(LOG_PREFIX_CLIENT + "getResponseBodyAsString = " + e.getResponseBodyAsString());
        }

    }
}