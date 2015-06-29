package be.persgroep.pe.service.data.queries.embeddable;

public class FloatingTextQueries {
    private final String insertParagraphQuery;
    private final String insertTextQuery;
    private final String insertFloatingToEmbeddedContextsQuery;

    private static volatile FloatingTextQueries INSTANCE = null;

    private FloatingTextQueries() {
        this.insertParagraphQuery = this.constructInsertParagraphQuery();
        this.insertTextQuery = this.constructInsertTextQuery();
        this.insertFloatingToEmbeddedContextsQuery = this.constructInsertFloatingToEmbeddedContextsQuery();
    }

    public static FloatingTextQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (FloatingTextQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FloatingTextQueries();
                }
            }
        }

        return INSTANCE;
    }

    public String getInsertParagraphQuery() {
        return insertParagraphQuery;
    }

    public String getInsertTextQuery() {
        return insertTextQuery;
    }

    public String getInsertFloatingToEmbeddedContextsQuery() {
        return insertFloatingToEmbeddedContextsQuery;
    }

    private String constructInsertParagraphQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_PARAGRAPHS");
        sql.append("  ( ");
        sql.append("    ID, ");
        sql.append("    TITLE ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    ? ,");
        sql.append("    ?  ");
        sql.append("  )");

        return sql.toString();
    }

    private String constructInsertTextQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_TEXTS ");
        sql.append("  ( ");
        sql.append("    ID, ");
        sql.append("    TEXT, ");
        sql.append("    POSITION, ");
        sql.append("    PARAGRAPH_ID ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    TEXTS_ID_SEQ.NEXTVAL , ");
        sql.append("    ? , ");
        sql.append("    1 , ");
        sql.append("    ?   ");
        sql.append("  )");

        return sql.toString();
    }

    private String constructInsertFloatingToEmbeddedContextsQuery() {
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
        sql.append("    EMBEDDED_PARAGRAPH_ID ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    EMBEDDED_CONTEXTS_ID_SEQ.NEXTVAL ,");
        sql.append("    ? ,");
        sql.append("    5 ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    1 ,");
        sql.append("    ?  ");
        sql.append("  )");

        return sql.toString();
    }

}
