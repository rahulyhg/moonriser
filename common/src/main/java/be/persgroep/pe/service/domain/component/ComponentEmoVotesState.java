package be.persgroep.pe.service.domain.component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ComponentEmoVotesState {
    DISABLED(1),
    ENABLED(2);

    private final int id;

    private ComponentEmoVotesState(final int id) {
        this.id = id;
    }

    @JsonCreator
    public static final ComponentEmoVotesState fromValue(final String value) {
        return ComponentEmoVotesState.valueOf(value);
    }

    public int getId() {
        return this.id;
    }

    @JsonValue
    public final String value() {
        return super.name();
    }

}
