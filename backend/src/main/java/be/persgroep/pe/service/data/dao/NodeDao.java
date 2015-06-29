package be.persgroep.pe.service.data.dao;

import be.persgroep.pe.service.data.mapper.SelectSearchNodesMapper;
import be.persgroep.pe.service.data.queries.NodeQueries;
import be.persgroep.pe.service.data.queries.QueryUtils;
import be.persgroep.pe.service.domain.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Primary
@Repository
public class NodeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void deleteAllNodeLinksForComponent(final int componentId) {
        this.jdbcTemplate.update(
                NodeQueries.getInstance().getDeleteAllNodeLinksForComponent(),
                componentId);
    }

    public void linkNodeToComponent(final int nodeId, final int componentId, final boolean masterNode, final int weight) {
        this.jdbcTemplate.update(
                NodeQueries.getInstance().getLinkNodeToComponent(),
                nodeId,
                componentId,
                masterNode,
                weight);
    }

    public List<Node> searchNodes(final Locale locale, final String query) {
        if (query == null || query.isEmpty()) {
            return new ArrayList<>();
        }

        return this.jdbcTemplate.query(
                NodeQueries.getInstance().getSearchNodes(),
                new SelectSearchNodesMapper(),
                QueryUtils.getInstance().convertLocaleToString(locale),
                QueryUtils.getInstance().surroundWithWildcards(query));
    }
}
