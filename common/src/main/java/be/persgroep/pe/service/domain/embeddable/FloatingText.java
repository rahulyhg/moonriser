package be.persgroep.pe.service.domain.embeddable;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.wordnik.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
@ApiModel("Floating")
public class FloatingText extends Embeddable {
    @NotBlank
    @Length(max = 1000)
    private String title;
    @NotBlank
    @Length(max = 4000)
    private String text;

    public FloatingText() {
    }

    public FloatingText(String text) {
        this.text = text;
    }

    public FloatingText(String title, String text) {
        this.text = text;
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
