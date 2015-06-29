package be.persgroep.pe.service.domain.wrapper;

import javax.validation.constraints.NotNull;

/**
 * Created by jlust on 4/12/14.
 * <p/>
 * articleId is the identifier of the article that has been created
 * errorMessage is the message containing details of what went wrong
 * errorCode is the identifier of the message. This is a value the client, interfacing with the service, can search for.
 */
public class SaveArticleResponse {
    @NotNull
    private Integer articleId;

    // constructors

    public SaveArticleResponse() {
    }

    public SaveArticleResponse(Integer articleId) {
        this.articleId = articleId;
    }

    // getters and setters

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getArticleId() {
        return articleId;
    }

}
