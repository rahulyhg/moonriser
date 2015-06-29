package be.persgroep.pe.service.domain.embeddable.jsonrepresentable;


import be.persgroep.pe.service.domain.embeddable.Embeddable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class SpotifyUri extends Embeddable implements JsonRepresentable {
    private SpotifyUriDisplayType displayType;
    private String uri;

    public SpotifyUriDisplayType getDisplayType() {
        return displayType;
    }

    public void setDisplayType(SpotifyUriDisplayType displayType) {
        this.displayType = displayType;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    public static enum SpotifyUriDisplayType {
        SMALL("small"),
        BIG("big");

        private final String spotifyUriDisplayType;

        SpotifyUriDisplayType(String spotifyUriDisplayType) {
            this.spotifyUriDisplayType = spotifyUriDisplayType;
        }

        public String getSpotifyUriDisplayType() {
            return spotifyUriDisplayType;
        }

        @JsonCreator
        public static final SpotifyUriDisplayType fromValue(String value) {
            return SpotifyUriDisplayType.valueOf(value);
        }

        public String getName() {
            return this.spotifyUriDisplayType;
        }

        @JsonValue
        public final String value() {
            return super.name();
        }
    }

    @Override
    public String toJsonString() {
        final StringBuilder jsonString = new StringBuilder();

        jsonString.append("{");
        jsonString.append("\"uri\"");
        jsonString.append(":");
        jsonString.append("\"" + this.uri + "\"");
        jsonString.append(",");
        jsonString.append("\"size\"");
        jsonString.append(":");
        jsonString.append("\"" + this.displayType.getSpotifyUriDisplayType() + "\"");
        jsonString.append("}");

        return jsonString.toString();
    }
}
