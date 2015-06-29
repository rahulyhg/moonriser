package be.persgroep.pe.service.data.statement;

import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by gheylen on 2/01/2015.
 */
public class InsertContainerTextToEmbeddedContextsSetter implements PreparedStatementSetter {
    private final Integer id;
    private final Integer embeddedParagraphId;
    private final Integer embedContainerId;

    public InsertContainerTextToEmbeddedContextsSetter(final Integer id, final Integer embeddedParagraphId, final Integer embedContainerId) {
        super();

        this.id = id;
        this.embeddedParagraphId = embeddedParagraphId;
        this.embedContainerId = embedContainerId;
    }

    @Override
    public void setValues(final PreparedStatement ps) throws SQLException {
        ps.setObject(1, this.id);
        ps.setObject(2, this.embeddedParagraphId);
        ps.setObject(3, this.embedContainerId);
    }
}
