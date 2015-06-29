package be.persgroep.pe.service.domain.wrapper;

import be.persgroep.pe.service.domain.component.article.Article;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jlust on 4/12/14.
 */
@ApiModel(value = "saveArticleRequest", description = "Article contents and publishing information")
public class PostArticleRequest {
    @ApiModelProperty(value="navigationIds", notes = "List of ids of the navigations this article should be linked to. These navigations also determine what publications the article will appear on.")
    private final Set<Integer> navigationIds;
    @ApiModelProperty(value="nodes", notes = "List of ids of nodes this article should be linked to.")
    private final Set<Integer> nodeIds;
    @NotNull
    @ApiModelProperty(value = "article", required = true)
    @Valid
    private Article article;
    @NotNull
    @ApiModelProperty(value = "masterNode", required = true)
    private Integer masterNode;

    public PostArticleRequest() {
        this.nodeIds = new HashSet<Integer>();
        this.navigationIds = new HashSet<Integer>();
    }

    public PostArticleRequest(Article article, Integer masterNode) {
        this();
        this.article = article;
        setMasterNode(masterNode);
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getMasterNode() {
        return masterNode;
    }

    public void setMasterNode(Integer masterNode) {
        this.masterNode = masterNode;
        this.nodeIds.add(masterNode);
    }

    public Set<Integer> getNavigationIds() {
        return navigationIds;
    }

    public Set<Integer> getNodeIds() {
        return nodeIds;
    }
}
