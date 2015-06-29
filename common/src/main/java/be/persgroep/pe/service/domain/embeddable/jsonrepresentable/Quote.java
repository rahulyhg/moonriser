package be.persgroep.pe.service.domain.embeddable.jsonrepresentable;

import be.persgroep.pe.service.domain.embeddable.Embeddable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("Quote")
public class Quote extends Embeddable implements JsonRepresentable {
    @NotNull
    @Length(max = 4000)
    @ApiModelProperty(value = "The name of the quoter", required = true)
    private String quoter;
    @NotNull
    @Length(max = 4000)
    @ApiModelProperty(value = "The text of the Quote", required = true)
    private String text;

    public String getQuoter() {
        return quoter;
    }

    public void setQuoter(String quoter) {
        this.quoter = quoter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("Quote [quoter=");
        builder.append(this.quoter);
        builder.append(", text=");
        builder.append(this.text);
        builder.append("]");

        return builder.toString();
    }

    @Override
    public String toJsonString() {
        final StringBuilder jsonString = new StringBuilder();

        jsonString.append("{");
        jsonString.append("\"text\"");
        jsonString.append(":");
        jsonString.append("\"" + this.text + "\"");
        jsonString.append(",");
        jsonString.append("\"quotee\"");
        jsonString.append(":");
        jsonString.append("\"" + this.quoter + "\"");
        jsonString.append("}");

        return jsonString.toString();
    }
}
