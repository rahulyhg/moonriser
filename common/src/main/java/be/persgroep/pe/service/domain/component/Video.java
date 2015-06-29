package be.persgroep.pe.service.domain.component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Video extends Component {
    private String externalId;
    private int originalHeight;
    private int originalWidth;
    private String text;
    private String title;
    private String url;

    @Override
    public ComponentType getComponentType() {
        return ComponentType.VIDEO;
    }
}
