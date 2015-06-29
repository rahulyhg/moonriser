package be.persgroep.pe.service.data.queries.embeddable;

public class GridQueries {
    private final String insertRowToTableRowsQuery;
    private final String insertGridToEmbeddedContextsQuery;

    private static volatile GridQueries INSTANCE = null;

    private GridQueries() {
        this.insertRowToTableRowsQuery = this.constructInsertRowToTableRowsQuery();
        this.insertGridToEmbeddedContextsQuery = this.constructInsertGridToEmbeddedContextsQuery();
    }

    public static GridQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (GridQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GridQueries();
                }
            }
        }

        return INSTANCE;
    }

    public String getInsertRowToTableRows() {
        return this.insertRowToTableRowsQuery;
    }

    public String getInsertGridToEmbeddedContexts() {
        return this.insertGridToEmbeddedContextsQuery;
    }

    private String constructInsertRowToTableRowsQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_TABLE_ROWS");
        sql.append("  ( ");
        sql.append("    TABLE_ID, ");
        sql.append("    TABLE_ROW, ");
        sql.append("    TABLE_COLUMN, ");
        sql.append("    VALUE ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ?  ");
        sql.append("  )");

        return sql.toString();
    }

    private String constructInsertGridToEmbeddedContextsQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_EMBEDDED_CONTEXTS");
        sql.append("  ( ");
        sql.append("    ID, ");
        sql.append("    EMBED_CONTAINER_ID, ");
        sql.append("    EMBEDDED_OBJECT_TYPE_ID, ");
        sql.append("    EMBED_ZONE_TYPE_ID, ");
        sql.append("    RANKING, ");
        sql.append("    EMBED_CTX_DISPLAY_TYPE_ID, ");
        sql.append("    EMBEDDED_TABLE_ID ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    EMBEDDED_CONTEXTS_ID_SEQ.NEXTVAL ,");
        sql.append("    ? ,");
        sql.append("    8 ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    1 ,");
        sql.append("    ?  ");
        sql.append("  )");

        return sql.toString();
    }
}
