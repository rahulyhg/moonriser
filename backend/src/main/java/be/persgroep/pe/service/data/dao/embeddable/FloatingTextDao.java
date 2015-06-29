package be.persgroep.pe.service.data.dao.embeddable;

import be.persgroep.pe.service.data.queries.SequenceQueries;
import be.persgroep.pe.service.data.queries.embeddable.FloatingTextQueries;
import be.persgroep.pe.service.domain.embeddable.FloatingText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class FloatingTextDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertFloating(final FloatingText floatingText, final int containerId) {
        final int paragraphId = this.jdbcTemplate.queryForObject(SequenceQueries.getInstance().getNextPeParagraphsId(), Integer.class);

        this.jdbcTemplate.update(
                FloatingTextQueries.getInstance().getInsertParagraphQuery(),
                paragraphId,
                floatingText.getTitle());

        this.jdbcTemplate.update(
                FloatingTextQueries.getInstance().getInsertTextQuery(),
                floatingText.getText(),
                paragraphId);

        this.jdbcTemplate.update(
                FloatingTextQueries.getInstance().getInsertFloatingToEmbeddedContextsQuery(),
                containerId,
                floatingText.getAlignment().getId(),
                floatingText.getRanking(),
                paragraphId);

        return paragraphId;
    }

}
