package be.persgroep.pe.service.data.statement;

import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by gheylen on 2/01/2015.
 */
public class InsertContainerTextToPeTextsSetter implements PreparedStatementSetter {
    private final Integer id;
    private final String text;
    private final Integer paragraphId;

    public InsertContainerTextToPeTextsSetter(final Integer id, final String text, final Integer paragraphId) {
        super();

        this.id = id;
        this.text = text;
        this.paragraphId = paragraphId;
    }

    @Override
    public void setValues(final PreparedStatement ps) throws SQLException {
        ps.setObject(1, this.id);
        ps.setString(2, this.text);
        ps.setObject(3, this.paragraphId);
    }
}
