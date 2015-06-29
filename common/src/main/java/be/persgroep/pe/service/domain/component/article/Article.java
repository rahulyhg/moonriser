package be.persgroep.pe.service.domain.component.article;

import be.persgroep.pe.service.domain.component.Component;
import be.persgroep.pe.service.domain.component.ComponentType;
import be.persgroep.pe.service.domain.container.MediaContainer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("Article")
public class Article extends Component {
    @Valid
    @ApiModelProperty(value = "The containers inside the article of type MEDIA")
    private final List<MediaContainer> containers;

    @NotBlank
    @Length(max = 255)
    @ApiModelProperty(value = "The title of the article", required = true)
    private String title;

    @Length(max = 255)
    @ApiModelProperty(value = "The subtitle of the article", required = false)
    private String subtitle;

    @NotNull
    @Length(max = 3200)
    @ApiModelProperty(value = "The introduction of the article", required = true)
    private String introduction;

    @Range(min = 0, max = 5)
    @ApiModelProperty(value = "The rating of the article (0-5)", required = false)
    private Integer starRating;

    @ApiModelProperty(value = "The label of the article", required = false)
    private ArticleLabelType labelType;

    @Length(max = 255)
    @ApiModelProperty(value = "The text of the free text label", required = false, notes = "This will automatically set labelType to \"free\".")
    private String freeTextLabel;

    @ApiModelProperty(value = "Whether or not this is an HLN Regio article", required = false)
    private Boolean regional;

    @ApiModelProperty(value = "The page number in the printed paper", required = false, notes = "Only applies to HLN Regio")
    private Integer printPageNumber;

    @Length(max = 255)
    @ApiModelProperty(value = "The type of the article in the printed paper", required = false, notes = "Only applies to HLN Regio")
    private String printType;

    //TODO: REFACTOR FROM HERE:
    @ApiModelProperty(value = "The id of the culture object in culture service", required = false)
    private Integer cultureRefId;
    @NotNull
    @ApiModelProperty(value = "The type of the article", required = true)
    private ArticleType articleType;
    @ApiModelProperty(value = "The type of the culture object in the culture service")
    private ArticleCultureType cultureType;

    public Article() {
        this.containers = new ArrayList<>();
    }

    public final List<MediaContainer> getMediaContainers() {
        return this.containers;
    }

    public void addMediaContainer(final MediaContainer container) {
        this.containers.add(container);
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(final String introduction) {
        this.introduction = introduction;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(final String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Integer getStarRating() {
        return this.starRating;
    }

    public void setStarRating(final Integer starRating) {
        this.starRating = starRating;
    }

    public ArticleLabelType getLabelType() {
        return this.labelType;
    }

    public void setLabelType(final ArticleLabelType labelType) {
        this.labelType = labelType;
    }

    public String getFreeTextLabel() {
        return this.freeTextLabel;
    }

    public void setFreeTextLabel(final String freeTextLabel) {
        this.freeTextLabel = freeTextLabel;

        if (freeTextLabel != null && !freeTextLabel.isEmpty()) {
            this.labelType = ArticleLabelType.FREE;
        }
    }

    public Boolean isRegional() {
        return this.regional;
    }

    public void setRegional(final Boolean isRegional) {
        this.regional = isRegional;
    }

    public Integer getPrintPageNumber() {
        return this.printPageNumber;
    }

    public void setPrintPageNumber(final Integer printPageNumber) {
        this.printPageNumber = printPageNumber;
    }

    public String getPrintType() {
        return this.printType;
    }

    public void setPrintType(final String printType) {
        this.printType = printType;
    }

    //TODO: REFACTOR FROM HERE:

    public ArticleType getArticleType() {
        return this.articleType;
    }

    public void setArticleType(final ArticleType articleType) {
        this.articleType = articleType;
    }

    public Integer getCultureRefId() {
        return this.cultureRefId;
    }

    public void setCultureRefId(final Integer cultureRefId) {
        this.cultureRefId = cultureRefId;
    }

    public ArticleCultureType getCultureType() {
        return this.cultureType;
    }

    public void setCultureType(final ArticleCultureType cultureType) {
        this.cultureType = cultureType;
    }


    @Override
    public ComponentType getComponentType() {
        return ComponentType.ARTICLE;
    }
}
