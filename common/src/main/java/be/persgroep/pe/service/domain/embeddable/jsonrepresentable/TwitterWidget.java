package be.persgroep.pe.service.domain.embeddable.jsonrepresentable;

import be.persgroep.pe.service.domain.embeddable.Embeddable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("TwitterWidget")
public class TwitterWidget extends Embeddable implements JsonRepresentable {
    @NotNull
    @Length(max = 4000)
    @ApiModelProperty(value = "The user of the Twitter Widget", required = true)
    private String twitterWidgetUserName;
    @NotNull
    @Length(max = 4000)
    @ApiModelProperty(value = "The id of the Twitter Widget", required = true)
    private String twitterWidgetId;

    public String getTwitterWidgetUserName() {
        return twitterWidgetUserName;
    }

    public void setTwitterWidgetUserName(String twitterWidgetUserName) {
        this.twitterWidgetUserName = twitterWidgetUserName;
    }

    public String getTwitterWidgetId() {
        return twitterWidgetId;
    }

    public void setTwitterWidgetId(String twitterWidgetId) {
        this.twitterWidgetId = twitterWidgetId;
    }

    @Override
    public String toString() {
        return "TwitterWidget{" +
                "twitterWidgetUserName='" + twitterWidgetUserName + '\'' +
                ", twitterWidgetId='" + twitterWidgetId + '\'' +
                '}';
    }

    @Override
    public String toJsonString() {
        final StringBuilder jsonString = new StringBuilder();

        jsonString.append("{");
        jsonString.append("\"username\"");
        jsonString.append(":");
        jsonString.append("\"" + this.twitterWidgetUserName + "\"");
        jsonString.append(",");
        jsonString.append("\"dataWidgetId\"");
        jsonString.append(":");
        jsonString.append("\"" + this.twitterWidgetId + "\"");
        jsonString.append("}");

        return jsonString.toString();
    }
}
