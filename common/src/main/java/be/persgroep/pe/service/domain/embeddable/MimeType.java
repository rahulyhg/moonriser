package be.persgroep.pe.service.domain.embeddable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MimeType {
    IMAGE_JPEG("image/jpeg", "jpg"), IMAGE_GIF("image/gif", "gif"), IMAGE_PNG("image/png", "png");

    private final String name;
    private final String extension;

    private MimeType(final String name, final String extension) {
        this.name = name;
        this.extension = extension;
    }

    @JsonCreator
    public static final MimeType getMimeType(final String value) {
        for (final MimeType mimeType : MimeType.values()) {
            if (mimeType.name.equals(value)) {
                return mimeType;
            }
        }

        return null;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }

    public String getExtension() {
        return this.extension;
    }
}
