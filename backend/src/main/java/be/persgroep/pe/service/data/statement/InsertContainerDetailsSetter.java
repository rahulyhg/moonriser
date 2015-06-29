package be.persgroep.pe.service.data.statement;

import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by gheylen on 2/01/2015.
 */
public class InsertContainerDetailsSetter implements PreparedStatementSetter {
    private final Integer id;
    private final Integer componentId;
    private final Integer embeddedContainerTypeId;
    private final Integer ranking;
    private final String title;

    public InsertContainerDetailsSetter(final Integer id, final Integer componentId, final Integer embeddedContainerTypeId, final Integer ranking, final String title) {
        super();

        this.id = id;
        this.componentId = componentId;
        this.embeddedContainerTypeId = embeddedContainerTypeId;
        this.ranking = ranking;
        this.title = title;
    }

    @Override
    public void setValues(final PreparedStatement ps) throws SQLException {
        ps.setObject(1, this.id);
        ps.setObject(2, this.componentId);
        ps.setObject(3, this.embeddedContainerTypeId);
        ps.setObject(4, this.ranking);
        ps.setObject(5, this.title);
    }
}
