package be.persgroep.pe.service.domain.component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ComponentRatingState {
    DISABLED(1),
    ENABLED(2);

    private final int id;

    private ComponentRatingState(final int id) {
        this.id = id;
    }

    @JsonCreator
    public static final ComponentRatingState fromValue(final String value) {
        return ComponentRatingState.valueOf(value);
    }

    public int getId() {
        return this.id;
    }

    @JsonValue
    public final String value() {
        return super.name();
    }
}
