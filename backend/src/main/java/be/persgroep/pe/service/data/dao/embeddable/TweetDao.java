package be.persgroep.pe.service.data.dao.embeddable;

import be.persgroep.pe.service.data.queries.SequenceQueries;
import be.persgroep.pe.service.data.queries.embeddable.jsonobject.TweetQueries;
import be.persgroep.pe.service.domain.embeddable.jsonrepresentable.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class TweetDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertTweet(final Tweet tweet, final int containerId) {
        final Integer jsonObjectId = jdbcTemplate.queryForObject(SequenceQueries.getInstance().getNextPeJsonObjectId(), Integer.class);

        this.jdbcTemplate.update(
                TweetQueries.getInstance().getInsertTwitterDetailsToJsonObjectQuery(),
                jsonObjectId,
                tweet.toJsonString());

        this.jdbcTemplate.update(
                TweetQueries.getInstance().getInsertJsonObjectToEmbeddedContexts(),
                containerId,
                tweet.getAlignment().getId(),
                tweet.getRanking(),
                jsonObjectId);

        return jsonObjectId;
    }

}
