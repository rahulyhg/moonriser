package be.persgroep.pe.service.domain.component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.joda.time.DateTime;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Poll extends Component {
    private DateTime endDateTime;
    private Set<PollOption> options;
    private DateTime startDateTime;

    @Override
    public ComponentType getComponentType() {
        return ComponentType.POLL;
    }

    public static class PollOption {
        private String name;
        private int votes;
    }


}
