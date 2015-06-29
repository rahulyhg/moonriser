package be.persgroep.pe.service.data.dao.embeddable;

import be.persgroep.pe.service.data.queries.SequenceQueries;
import be.persgroep.pe.service.data.queries.embeddable.jsonobject.SpotifyUriQueries;
import be.persgroep.pe.service.domain.embeddable.jsonrepresentable.SpotifyUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class SpotifyDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertSpotifyUri(final SpotifyUri spotifyUri, final int containerId) {
        final int jsonObjectId = this.jdbcTemplate.queryForObject(SequenceQueries.getInstance().getNextPeJsonObjectId(), Integer.class);

        this.jdbcTemplate.update(
                SpotifyUriQueries.getInstance().getInsertSpotifyDetailsToJsonObjectQuery(),
                jsonObjectId,
                spotifyUri.toJsonString());

        this.jdbcTemplate.update(
                SpotifyUriQueries.getInstance().getInsertJsonObjectToEmbeddedContexts(),
                containerId,
                spotifyUri.getAlignment().getId(),
                spotifyUri.getRanking(),
                jsonObjectId);

        return jsonObjectId;
    }

}
