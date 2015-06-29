package be.persgroep.pe.service.domain.component.article;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

//TODO: Suggested optimization.
//TODO: Not everything is and will be supported through moonriser? Hide some options for client?
//TODO: E.g.: Recipe? Should be transparant, or not available at all.
public enum ArticleType {
    DEFAULT(1),
    BACKGROUND(2),
    FACTFILE(3),
    OPINION(5),
    FREE_HTML(6),
    RECIPE(7),
    LIVE(8),
    ADVERTORIAL(9),
    REVIEW(10),
    REVIEW_EXTENDED(11),
    EXTENDED(12),
    RUBRIC(13),
    COLUMN(14),
    REGIO(15);

    private final int id;

    private ArticleType(final int id) {
        this.id = id;
    }

    @JsonCreator
    public static final ArticleType fromValue(final String value) {
        return ArticleType.valueOf(value);
    }

    public int getId() {
        return this.id;
    }

    @JsonValue
    public final String value() {
        return super.name();
    }

}
