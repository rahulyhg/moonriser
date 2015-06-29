package be.persgroep.pe.service.domain.component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoAlbum extends Component {
    private String description;
    private int maxPhotos;
    private String title;

    @Override
    public ComponentType getComponentType() {
        return ComponentType.PHOTOALBUM;
    }
}
