package be.persgroep.pe.service.domain.component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

//TODO: Suggested for removal: We have different types, why making them explicit in another property?
public enum ComponentType {
    ARTICLE(20), POLL(24), URL(25), PHOTOALBUM(26), VIDEO(30);

    private final int id;

    private ComponentType(final int id) {
        this.id = id;
    }

    @JsonCreator
    public static final ComponentType fromValue(String value) {
        return ComponentType.valueOf(value);
    }

    public int getId() {
        return this.id;
    }

    @JsonValue
    public final String value() {
        return name();
    }
}
