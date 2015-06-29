package be.persgroep.pe.service.data.mapper;

import be.persgroep.pe.service.data.queries.QueryUtils;
import be.persgroep.pe.service.domain.Node;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectSearchNodesMapper implements RowMapper<Node> {
    @Override
    public Node mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Node node = new Node();

        node.setId(rs.getInt(1));
        node.setVisibleOnSite(rs.getBoolean(2));
        node.setVisible(rs.getBoolean(3));
        node.setTitle(rs.getString(4));
        node.setDescription(rs.getString(5));
        node.setLocale(QueryUtils.getInstance().convertStringToLocale(rs.getString(6)));

        return node;
    }
}
