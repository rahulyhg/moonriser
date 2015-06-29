package be.persgroep.pe.service.data.dao.embeddable;

import be.persgroep.pe.service.data.queries.SequenceQueries;
import be.persgroep.pe.service.data.queries.embeddable.jsonobject.QuoteQueries;
import be.persgroep.pe.service.domain.embeddable.jsonrepresentable.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class QuoteDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertQuote(final Quote quote, final int containerId) {
        final int jsonObjectId = this.jdbcTemplate.queryForObject(SequenceQueries.getInstance().getNextPeJsonObjectId(), Integer.class);

        this.jdbcTemplate.update(
                QuoteQueries.getInstance().getInsertQuoteDetailsToJsonObjectQuery(),
                jsonObjectId,
                quote.toJsonString());

        this.jdbcTemplate.update(
                QuoteQueries.getInstance().getInsertJsonObjectToEmbeddedContexts(),
                containerId,
                quote.getAlignment().getId(),
                quote.getRanking(),
                jsonObjectId);

        return jsonObjectId;
    }
}
