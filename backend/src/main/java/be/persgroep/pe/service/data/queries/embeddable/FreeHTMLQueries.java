package be.persgroep.pe.service.data.queries.embeddable;

public class FreeHTMLQueries {
    private final String insertFreeHTMLQuery;
    private final String insertFreeHTMLToEmbeddedContextsQuery;
    private final String insertFreeHTMLQueryWithTemplateNull;

    private static volatile FreeHTMLQueries INSTANCE = null;

    private FreeHTMLQueries() {
        this.insertFreeHTMLQuery = this.constructInsertFreeHTMLQuery();
        this.insertFreeHTMLToEmbeddedContextsQuery = this.constructInsertFreeHTMLToEmbeddedContextsQuery();
        this.insertFreeHTMLQueryWithTemplateNull = this.constructInsertFreeHTMLQueryWithTemplateNull();
    }

    public static FreeHTMLQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (FreeHTMLQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FreeHTMLQueries();
                }
            }
        }

        return INSTANCE;
    }

    public String getInsertFreeHTMLQuery() {
        return insertFreeHTMLQuery;
    }

    public String getInsertFreeHTMLToEmbeddedContextsQuery() {
        return insertFreeHTMLToEmbeddedContextsQuery;
    }

    public String getInsertFreeHTMLQueryWithTemplateNull() {
        return insertFreeHTMLQueryWithTemplateNull;
    }

    private String constructInsertFreeHTMLQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_CODE_SNIPPETS");
        sql.append("  ( ");
        sql.append("    ID, ");
        sql.append("    CODE_TEMPLATE_ID,  ");
        sql.append("    FREE_TEXT  ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ?  ");
        sql.append("  )");

        return sql.toString();
    }

    private String constructInsertFreeHTMLQueryWithTemplateNull() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_CODE_SNIPPETS");
        sql.append("  ( ");
        sql.append("    ID, ");
        sql.append("    FREE_TEXT  ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    ? ,");
        sql.append("    ?  ");
        sql.append("  )");

        return sql.toString();
    }

    private String constructInsertFreeHTMLToEmbeddedContextsQuery() {
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
        sql.append("    EMBEDDED_CODE_SNIPPET_ID ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    EMBEDDED_CONTEXTS_ID_SEQ.NEXTVAL ,");
        sql.append("    ? ,");
        sql.append("    3 ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    1 ,");
        sql.append("    ?  ");
        sql.append("  )");

        return sql.toString();
    }
}
