package be.persgroep.pe.service.data.queries;

public class NodeQueries {
    private final String deleteAllNodeLinksForComponentQuery;
    private final String linkNodeToComponentQuery;
    private final String searchNodeQuery;

    private static volatile NodeQueries INSTANCE = null;

    private NodeQueries() {
        this.deleteAllNodeLinksForComponentQuery = this.constructDeleteAllNodeLinksForComponent();
        this.linkNodeToComponentQuery = this.constructLinkNodeToComponentQuery();
        this.searchNodeQuery = this.constructSearchNodesQuery();
    }

    public static NodeQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (NodeQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NodeQueries();
                }
            }
        }

        return INSTANCE;
    }

    public String getDeleteAllNodeLinksForComponent() {
        return this.deleteAllNodeLinksForComponentQuery;
    }

    public String getLinkNodeToComponent() {
        return this.linkNodeToComponentQuery;
    }

    public String getSearchNodes() {
        return this.searchNodeQuery;
    }

    private String constructDeleteAllNodeLinksForComponent() {
        return "DELETE FROM PE_COMPONENTS_MAP_NODES WHERE COMPONENT_ID = ?";
    }

    private String constructLinkNodeToComponentQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_COMPONENTS_MAP_NODES ");
        sql.append("  ( ");
        sql.append("    ID, ");
        sql.append("    NODE_ID, ");
        sql.append("    COMPONENT_ID, ");
        sql.append("    MASTER, ");
        sql.append("    WEIGHT ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    COMPONENTS_MAP_NODES_ID_SEQ.NEXTVAL, ");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ");
        sql.append("  )");

        return sql.toString();
    }

    private final String constructSearchNodesQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("SELECT n.ID, ");
        sql.append("  n.PARENT_ID, ");
        sql.append("  n.VISIBLE_ON_SITE, ");
        sql.append("  n.VISIBLE, ");
        sql.append("  l.TITLE, ");
        sql.append("  l.DESCRIPTION, ");
        sql.append("  l.LOCALE ");
        sql.append("FROM PE_NODES_LOCALIZED l ");
        sql.append("JOIN PE_NODES n ");
        sql.append("ON l.NODE_ID     = n.ID ");
        sql.append("WHERE n.LINKABLE = 1 ");
        sql.append("AND n.ACTIVE     = 1 ");
        sql.append("AND l.LOCALE     = ? ");
        sql.append("AND LOWER(l.TITLE) LIKE LOWER( ? ) ");
        sql.append("ORDER BY n.PARENT_ID NULLS FIRST, n.ID");

        return sql.toString();
    }
}
