package be.persgroep.pe.service.domain.embeddable.freehtml;


import be.persgroep.pe.service.domain.embeddable.Embeddable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.wordnik.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
@ApiModel("FreeHTML")
public class FreeHTML extends Embeddable {
    private FreeHTMLTemplateType freeHTMLTemplateType;
    @NotBlank
    @Length(max = 4000)
    private String text;

    public FreeHTML() {
    }

    public FreeHTML(String text) {
        this.text = text;
    }

    public FreeHTML(FreeHTMLTemplateType templateId, String text) {
        this.freeHTMLTemplateType = templateId;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public FreeHTMLTemplateType getFreeHTMLTemplateType() {
        return freeHTMLTemplateType;
    }

    public void setFreeHTMLTemplateType(FreeHTMLTemplateType freeHTMLTemplateType) {
        this.freeHTMLTemplateType = freeHTMLTemplateType;
    }
}
