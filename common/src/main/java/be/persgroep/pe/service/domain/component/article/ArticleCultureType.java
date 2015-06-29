package be.persgroep.pe.service.domain.component.article;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

//TODO: Should be more transparant? Is linked to culture item id in article. They should be processor both at once from client.
public enum ArticleCultureType {
    FILM(4),
    EVENT(3),
    LOCATION(2),
    ITEM(1);

    private final int id;

    private ArticleCultureType(final int id) {
        this.id = id;
    }

    @JsonCreator
    public static final ArticleCultureType fromValue(final String value) {
        return ArticleCultureType.valueOf(value);
    }

    public int getId() {
        return this.id;
    }

    @JsonValue
    public final String value() {
        return super.name();
    }
}
