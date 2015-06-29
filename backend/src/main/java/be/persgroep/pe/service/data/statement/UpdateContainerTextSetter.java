package be.persgroep.pe.service.data.statement;

import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by gheylen on 26/12/2014.
 */
public class UpdateContainerTextSetter implements PreparedStatementSetter {
    private final String text;
    private final Integer containerId;

    public UpdateContainerTextSetter(final String text, final Integer containerId) {
        super();

        this.text = text;
        this.containerId = containerId;
    }

    @Override
    public void setValues(final PreparedStatement ps) throws SQLException {
        ps.setString(1, this.text);
        ps.setInt(2, this.containerId);
    }
}
