package be.persgroep.pe.service.data.dao.embeddable;

import be.persgroep.pe.service.data.queries.SequenceQueries;
import be.persgroep.pe.service.data.queries.embeddable.GridQueries;
import be.persgroep.pe.service.domain.embeddable.grid.Grid;
import be.persgroep.pe.service.domain.embeddable.grid.GridRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class GridDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertGrid(final Grid grid, final int containerId) {
        final Integer gridId = this.jdbcTemplate.queryForObject(SequenceQueries.getInstance().getNextPeTableRowId(), Integer.class);

        for (final GridRow gridRow : grid.getGridRowArrayList()) {
            this.jdbcTemplate.update(
                    GridQueries.getInstance().getInsertRowToTableRows(),
                    gridId,
                    gridRow.getTableRowId(),
                    gridRow.getTableColumnId(),
                    gridRow.getValue());
        }

        this.jdbcTemplate.update(
                GridQueries.getInstance().getInsertGridToEmbeddedContexts(),
                containerId,
                grid.getAlignment().getId(),
                grid.getRanking(),
                gridId);

        return gridId;
    }

}
