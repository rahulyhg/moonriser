package be.persgroep.pe.service.data.dao.embeddable;

import be.persgroep.pe.service.data.queries.embeddable.EmbeddableComponentQueries;
import be.persgroep.pe.service.domain.embeddable.EmbeddedComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class EmbeddableComponentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertEmbeddableComponent(final EmbeddedComponent embeddedComponent, final int containerId) {
        this.jdbcTemplate.update(
                EmbeddableComponentQueries.getInstance().getInsertComponentToEmbeddedContextsQuery(),
                containerId,
                embeddedComponent.getAlignment().getId(),
                embeddedComponent.getRanking(),
                embeddedComponent.getComponentId());
    }
}
