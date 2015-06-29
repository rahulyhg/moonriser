package be.persgroep.pe.service.domain.embeddable.jsonrepresentable;

import be.persgroep.pe.service.domain.embeddable.Embeddable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("Tweet")
public class Tweet extends Embeddable implements JsonRepresentable {
    @NotNull
    @Length(max = 4000)
    @ApiModelProperty(value = "The text of the tweet", required = true)
    private String text;
    @NotNull
    @Length(max = 4000)
    @ApiModelProperty(value = "The author of the tweet", required = true)
    private String author;
    @NotNull
    @Length(max = 4000)
    @ApiModelProperty(value = "The Twitter username of the tweet", required = true)
    private String username;
    @NotNull
    @ApiModelProperty(value = "The identifier of the tweet", required = true)
    private String identifier;
    @NotNull
    @ApiModelProperty(value = "The timestamp of the tweet", required = true)
    private Long timestamp;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toJsonString() {
        final StringBuilder jsonString = new StringBuilder();

        jsonString.append("{");
        jsonString.append("\"text\"");
        jsonString.append(":");
        jsonString.append("\"" + this.text + "\"");
        jsonString.append(",");
        jsonString.append("\"author\"");
        jsonString.append(":");
        jsonString.append("\"" + this.author + "\"");
        jsonString.append(",");
        jsonString.append("\"username\"");
        jsonString.append(":");
        jsonString.append("\"" + this.username + "\"");
        jsonString.append(",");
        jsonString.append("\"identifier\"");
        jsonString.append(":");
        jsonString.append("\"" + this.identifier + "\"");
        jsonString.append(",");
        jsonString.append("\"timestamp\"");
        jsonString.append(":");
        jsonString.append("\"" + this.timestamp + "\"");
        jsonString.append("}");

        return jsonString.toString();
    }
}
