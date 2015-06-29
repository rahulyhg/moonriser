package be.persgroep.pe.service.domain.embeddable;

import be.persgroep.pe.service.domain.component.Component;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("EmbeddedComponent")
public class EmbeddedComponent extends Embeddable {
    @NotNull
    @ApiModelProperty(value = "The id of the component", required = true)
    private Integer componentId;

    public Integer getComponentId() {
        return componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }
}
