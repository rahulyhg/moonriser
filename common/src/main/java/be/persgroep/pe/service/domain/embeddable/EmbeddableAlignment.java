package be.persgroep.pe.service.domain.embeddable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EmbeddableAlignment {
    TOP_CENTER(2),
    MIDDLE_LEFT(4),
    MIDDLE_RIGHT(6),
    BOTTOM_CENTER(8),
    FLOATING(10);

    private final int id;

    private EmbeddableAlignment(final int id) {
        this.id = id;
    }

    @JsonCreator
    public static final EmbeddableAlignment fromValue(final String value) {
        return EmbeddableAlignment.valueOf(value);
    }

    public int getId() {
        return this.id;
    }

    @JsonValue
    public final String value() {
        return super.name();
    }
}
