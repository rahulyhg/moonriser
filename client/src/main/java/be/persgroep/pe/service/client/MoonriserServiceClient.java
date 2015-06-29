package be.persgroep.pe.service.client;

import be.persgroep.pe.service.domain.wrapper.PostArticleRequest;
import be.persgroep.pe.service.domain.wrapper.SaveArticleResponse;
import be.persgroep.pe.service.domain.wrapper.SearchNodesRequest;
import be.persgroep.pe.service.domain.wrapper.SearchNodesResponse;
import org.springframework.web.client.RestTemplate;

public class MoonriserServiceClient {
    private final String moonriserServiceUrlBe;
    private final String moonriserServiceUrlNl;
    private final RestTemplate restTemplate;

    public final Articles Articles = this.new Articles();
    public final Nodes Nodes = this.new Nodes();

    public MoonriserServiceClient(final String moonriserServiceUrlBe, final String moonriserServiceUrlNl) {
        this.moonriserServiceUrlBe = moonriserServiceUrlBe;
        this.moonriserServiceUrlNl = moonriserServiceUrlNl;
        this.restTemplate = new MoonriserRestTemplate();
    }

    public class Articles {
        public SaveArticleResponse postArticle(final PostArticleRequest request) {
            //TODO: Refactor: Make transparant by country
            return restTemplate.postForObject(moonriserServiceUrlBe + "/articles/", request, SaveArticleResponse.class);
        }

        public void deleteArticle(final Integer originatingKey) {
            //TODO: Refactor: Make transparant by country
            restTemplate.delete(moonriserServiceUrlBe + "/articles/" + originatingKey);
        }
    }

    public class Nodes {
        public SearchNodesResponse searchNodes(final SearchNodesRequest request) {
            final StringBuilder getRequest = new StringBuilder();
            getRequest.append(moonriserServiceUrlBe);
            getRequest.append("/nodes/");
            getRequest.append(request.getLocale());
            getRequest.append("?query=");
            getRequest.append(request.getQuery());

            return restTemplate.getForObject(getRequest.toString(), SearchNodesResponse.class);
        }
    }
}
