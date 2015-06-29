package be.persgroep.pe.service.domain.component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ContributorRole {
    CREATOR(1),
    EDITOR(2);

    private final int id;

    private ContributorRole(final int id) {
        this.id = id;
    }

    @JsonCreator
    public static final ContributorRole fromValue(final String value) {
        return ContributorRole.valueOf(value);
    }

    public int getId() {
        return this.id;
    }

    @JsonValue
    public final String value() {
        return super.name();
    }

}
