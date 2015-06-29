package be.persgroep.pe.service.domain.component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ComponentReactionState {
    DISABLED(1),
    ENABLED_WITH_APPROVAL(2),
    ENABLED_WITHOUT_APPROVAL(3);

    private final int id;

    private ComponentReactionState(final int id) {
        this.id = id;
    }

    @JsonCreator
    public static final ComponentReactionState fromValue(final String value) {
        return ComponentReactionState.valueOf(value);
    }

    public int getId() {
        return this.id;
    }

    @JsonValue
    public final String value() {
        return super.name();
    }
}
