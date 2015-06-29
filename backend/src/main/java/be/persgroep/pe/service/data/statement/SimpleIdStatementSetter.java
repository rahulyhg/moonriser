package be.persgroep.pe.service.data.statement;

import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by gheylen on 2/01/2015.
 */
public class SimpleIdStatementSetter implements PreparedStatementSetter {
    private final Integer id;

    public SimpleIdStatementSetter(final Integer id) {
        this.id = id;
    }

    @Override
    public void setValues(final PreparedStatement ps) throws SQLException {
        ps.setObject(1, this.id);
    }
}
