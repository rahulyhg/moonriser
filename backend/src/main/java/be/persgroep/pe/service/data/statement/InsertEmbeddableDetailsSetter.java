package be.persgroep.pe.service.data.statement;

import be.persgroep.pe.service.data.queries.QueryUtils;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by wvandenbrande on 26/12/2014.
 */
public class InsertEmbeddableDetailsSetter implements
        PreparedStatementSetter {
    private final Integer id;
    private final Integer containerId;
    private final Integer embedZoneTypeId;
    private final Integer ranking;
    private final Integer jsonObjectId;

    public InsertEmbeddableDetailsSetter(
            final Integer id,
            final Integer containerId,
            final Integer embedZoneTypeId,
            final Integer ranking,
            final Integer jsonObjectId) {
        this.id = id;
        this.containerId = containerId;
        this.embedZoneTypeId = embedZoneTypeId;
        this.ranking = ranking;
        this.jsonObjectId = jsonObjectId;
    }

    @Override
    public void setValues(final PreparedStatement ps) throws SQLException {
        ps.setInt(1, this.id);
        ps.setInt(2, this.containerId);
        ps.setInt(3, this.embedZoneTypeId);
        ps.setInt(4, this.ranking);
        ps.setInt(5, this.jsonObjectId);
    }
}
