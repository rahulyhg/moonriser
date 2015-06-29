package be.persgroep.pe.service.data.dao.embeddable;

import be.persgroep.pe.service.data.queries.SequenceQueries;
import be.persgroep.pe.service.data.queries.embeddable.jsonobject.TwitterWidgetQueries;
import be.persgroep.pe.service.domain.embeddable.jsonrepresentable.TwitterWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class TwitterWidgetDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertTwitterWidget(final TwitterWidget twitterWidget, final int containerId) {
        final int jsonObjectId = this.jdbcTemplate.queryForObject(SequenceQueries.getInstance().getNextPeJsonObjectId(), Integer.class);

        this.jdbcTemplate.update(
                TwitterWidgetQueries.getInstance().getInsertTwitterWidgetDetailsToJsonObjectQuery(),
                jsonObjectId,
                twitterWidget.toJsonString());

        this.jdbcTemplate.update(
                TwitterWidgetQueries.getInstance().getInsertJsonObjectToEmbeddedContexts(),
                containerId,
                twitterWidget.getAlignment().getId(),
                twitterWidget.getRanking(),
                jsonObjectId);

        return jsonObjectId;
    }

}
