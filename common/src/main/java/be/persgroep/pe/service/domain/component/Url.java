package be.persgroep.pe.service.domain.component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Url extends Component {
    private String description;
    private String title;
    private String url;

    @Override
    public ComponentType getComponentType() {
        return ComponentType.URL;
    }
}
