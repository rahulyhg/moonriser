package be.persgroep.pe.service.domain.component.article;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ArticleLabelType {
    REVIEW(5),
    OPINION(6),
    COLUMN(7),
    COMMENT(11),
    LIVE(12),
    VIDEO(13),
    FREE(14),
    BREAKING_NEWS(2),
    BACKGROUND(3),
    UPDATE(4),
    WEBLOG(8),
    USER_GENERATED_CONTENT(10),
    PROMO(9);

    private final int id;

    private ArticleLabelType(final int id) {
        this.id = id;
    }

    @JsonCreator
    public static final ArticleLabelType fromValue(final String value) {
        return ArticleLabelType.valueOf(value);
    }

    public int getId() {
        return this.id;
    }

    @JsonValue
    public final String value() {
        return super.name();
    }
}
