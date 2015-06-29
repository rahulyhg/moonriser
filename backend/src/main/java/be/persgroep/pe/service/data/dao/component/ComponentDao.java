package be.persgroep.pe.service.data.dao.component;

import be.persgroep.pe.service.data.queries.component.ComponentQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public abstract class ComponentDao {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected Environment env;

    public boolean existsComponent(final int componentId) {
        final List<Integer> result =
                this.jdbcTemplate.queryForList(
                        ComponentQueries.getInstance().getExistsComponent(),
                        Integer.class,
                        componentId);

        if (result.size() == 0) {
            return false;
        }

        return true;
    }

    public void archiveComponent(final int componentId) {
        this.jdbcTemplate.update(
                ComponentQueries.getInstance().getArchiveComponent(),
                componentId);
    }
}
