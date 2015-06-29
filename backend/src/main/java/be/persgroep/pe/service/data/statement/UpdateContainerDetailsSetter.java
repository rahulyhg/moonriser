package be.persgroep.pe.service.data.statement;

import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by gheylen on 26/12/2014.
 */
public class UpdateContainerDetailsSetter implements PreparedStatementSetter {
    private final Integer id;
    private final Integer typeId;
    private final Integer ranking;
    private final String title;

    public UpdateContainerDetailsSetter(final Integer id, final Integer typeId, final Integer ranking, final String title) {
        super();

        this.id = id;
        this.typeId = typeId;
        this.ranking = ranking;
        this.title = title;
    }


    @Override
    public void setValues(final PreparedStatement ps) throws SQLException {
        ps.setInt(1, this.typeId);
        ps.setInt(2, this.ranking);
        ps.setString(3, this.title);
        ps.setInt(4, this.id);
    }
}
