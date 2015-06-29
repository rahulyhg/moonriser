package be.persgroep.pe.service.domain.component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import java.util.*;

@ApiModel("Component")
public abstract class Component {
    private final Map<Integer, Long> commentCollectionIds;
    private final Set<Contributor> contributors;
    @ApiModelProperty(value = "The creation date of the component", required = true)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private DateTime creationDateTime;
    @NotNull
    private Long creationUserUuid;
    @ApiModelProperty(value = "Whether or not emo voting is enabled on the component", required = true)
    @NotNull
    private ComponentEmoVotesState emoVotesState;
    private Integer id;
    @ApiModelProperty(value = "The locale of the component", required = true)
    @NotNull
    private Locale locale;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private DateTime modificationDateTime;
    private Long modificationUserUuid;
    @Length(max = 256)
    private String originatingKey;
    private boolean paidContent;
    private boolean plusContent;
    @ApiModelProperty(value = "The publication end date of the component", required = true)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private DateTime publicationEndDateTime;
    @ApiModelProperty(value = "The publication start date of the component", required = true)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private DateTime publicationStartDateTime;
    @ApiModelProperty(value = "Whether or not rating is enabled on the component", required = true)
    @NotNull
    private ComponentRatingState ratingState;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private DateTime reactionEndDateTime;
    @ApiModelProperty(value = "Whether or not commenting is allowed, and if so, whether or not it's moderated", required = true)
    @NotNull
    private ComponentReactionState reactionState;
    @Length(max = 60)
    private String sourceText;
    @ApiModelProperty(value = "The state of the component", required = true)
    @NotNull
    private ComponentState state;

    public Component() {
        this.commentCollectionIds = new HashMap<Integer, Long>();
        this.contributors = new HashSet<Contributor>();
    }

    public void addCommentCollectionId(Integer publicationId,
                                       Long commentCollectionId) {
        commentCollectionIds.put(publicationId, commentCollectionId);
    }

    public void addContributor(Contributor contributor) {
        contributors.add(contributor);
    }

    public Map<Integer, Long> getCommentCollectionIds() {
        return commentCollectionIds;
    }

    public Set<Contributor> getContributors() {
        return contributors;
    }

    public DateTime getCreationDateTime() {
        return this.creationDateTime;
    }

    public void setCreationDateTime(final DateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Long getCreationUserUuid() {
        return creationUserUuid;
    }

    public ComponentEmoVotesState getEmoVotesState() {
        return emoVotesState;
    }

    public void setEmoVotesState(ComponentEmoVotesState emoVotesState) {
        this.emoVotesState = emoVotesState;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(final Locale locale) {
        this.locale = locale;
    }

    public DateTime getModificationDateTime() {
        return modificationDateTime;
    }

    public void setModificationDateTime(DateTime modificationDateTime) {
        this.modificationDateTime = modificationDateTime;
    }

    public Long getModificationUserUuid() {
        return modificationUserUuid;
    }
    public void setModificationUserUuid(Long modificationUserUUID) {
        this.modificationUserUuid = modificationUserUUID;
    }

    public String getOriginatingKey() {
        return originatingKey;
    }

    public void setOriginatingKey(String originatingKey) {
        this.originatingKey = originatingKey;
    }

    public DateTime getPublicationEndDateTime() {
        return publicationEndDateTime;
    }

    public void setPublicationEndDateTime(DateTime publicationEndDateTime) {
        this.publicationEndDateTime = publicationEndDateTime;
    }

    public DateTime getPublicationStartDateTime() {
        return publicationStartDateTime;
    }

    public void setPublicationStartDateTime(DateTime publicationStartDateTime) {
        this.publicationStartDateTime = publicationStartDateTime;
    }

    public ComponentRatingState getRatingState() {
        return ratingState;
    }

    public void setRatingState(ComponentRatingState ratingState) {
        this.ratingState = ratingState;
    }

    public DateTime getReactionEndDateTime() {
        return reactionEndDateTime;
    }

    public void setReactionEndDateTime(DateTime reactionEndDateTime) {
        this.reactionEndDateTime = reactionEndDateTime;
    }

    public ComponentReactionState getReactionState() {
        return reactionState;
    }

    public void setReactionState(ComponentReactionState reactionState) {
        this.reactionState = reactionState;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public ComponentState getState() {
        return state;
    }

    public void setState(ComponentState state) {
        this.state = state;
    }

    public boolean isPaidContent() {
        return paidContent;
    }

    public void setPaidContent(boolean paidContent) {
        this.paidContent = paidContent;
    }

    public boolean isPlusContent() {
        return plusContent;
    }

    public void setPlusContent(boolean plusContent) {
        this.plusContent = plusContent;
    }

    public void removeCommentCollectionId(Integer publicationId) {
        commentCollectionIds.remove(publicationId);
    }

    public void removeContributor(Contributor contributor) {
        contributors.remove(contributor);
    }

    public void setCreationUserUuid(Long creationUserUuid) {
        this.creationUserUuid = creationUserUuid;
    }

    public abstract ComponentType getComponentType();

    @Override
    public String toString() {
        return "Component{" +
                "commentCollectionIds=" + commentCollectionIds +
                ", contributors=" + contributors +
                ", creationDateTime=" + creationDateTime +
                ", creationUserUuid='" + creationUserUuid + '\'' +
                ", emoVotesState=" + emoVotesState +
                ", id=" + id +
                ", locale=" + locale +
                ", modificationDateTime=" + modificationDateTime +
                ", modificationUserUuid='" + modificationUserUuid + '\'' +
                ", originatingKey='" + originatingKey + '\'' +
                ", paidContent=" + paidContent +
                ", plusContent=" + plusContent +
                ", publicationEndDateTime=" + publicationEndDateTime +
                ", publicationStartDateTime=" + publicationStartDateTime +
                ", ratingState=" + ratingState +
                ", reactionEndDateTime=" + reactionEndDateTime +
                ", reactionState=" + reactionState +
                ", sourceText='" + sourceText + '\'' +
                ", state=" + state +
                '}';
    }
}
