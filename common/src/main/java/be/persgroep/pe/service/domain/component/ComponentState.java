package be.persgroep.pe.service.domain.component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

//TODO: Suggested for removal: These values should be transparant in the client actions, not exposed.
public enum ComponentState {
    CREATED(1),
    READY_FOR_PUBLICATION(2),
    APPROVED_FOR_PUBLICATION(3),
    ON_HOLD(4),
    ARCHIVED(5);

    private final int id;

    private ComponentState(final int id) {
        this.id = id;
    }

    @JsonCreator
    public static final ComponentState fromValue(String value) {
        return ComponentState.valueOf(value);
    }

    public int getId() {
        return this.id;
    }

    @JsonValue
    public final String value() {
        return name();
    }
}
