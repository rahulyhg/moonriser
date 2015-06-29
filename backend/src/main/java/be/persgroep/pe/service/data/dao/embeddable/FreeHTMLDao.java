package be.persgroep.pe.service.data.dao.embeddable;

import be.persgroep.pe.service.data.queries.SequenceQueries;
import be.persgroep.pe.service.data.queries.embeddable.FreeHTMLQueries;
import be.persgroep.pe.service.domain.embeddable.freehtml.FreeHTML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class FreeHTMLDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertFreeHTML(final FreeHTML freeHTML, final int containerId) {
        final int freeHMTLId = this.jdbcTemplate.queryForObject(SequenceQueries.getInstance().getNextCodeSnippetsId(), Integer.class);

        if (freeHTML.getFreeHTMLTemplateType() ==  null) {
            this.jdbcTemplate.update(
                    FreeHTMLQueries.getInstance().getInsertFreeHTMLQueryWithTemplateNull(),
                    freeHMTLId,
                    freeHTML.getText());
        }
        else {
            this.jdbcTemplate.update(
                    FreeHTMLQueries.getInstance().getInsertFreeHTMLQuery(),
                    freeHMTLId,
                    freeHTML.getFreeHTMLTemplateType().getId(),
                    freeHTML.getText());
        }

        this.jdbcTemplate.update(
                FreeHTMLQueries.getInstance().getInsertFreeHTMLToEmbeddedContextsQuery(),
                containerId,
                freeHTML.getAlignment().getId(),
                freeHTML.getRanking(),
                freeHMTLId);

        return freeHMTLId;
    }

}
