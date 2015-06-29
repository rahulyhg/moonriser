package be.persgroep.pe.service.data.queries.embeddable;

public class EmbeddableComponentQueries {
    private final String insertComponentToEmbeddedContextsQuery;

    private static volatile EmbeddableComponentQueries INSTANCE = null;

    private EmbeddableComponentQueries() {
        this.insertComponentToEmbeddedContextsQuery = this.constructInsertComponentToEmbeddedContextsQuery();
    }

    public static EmbeddableComponentQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (EmbeddableComponentQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EmbeddableComponentQueries();
                }
            }
        }

        return INSTANCE;
    }

    public String getInsertComponentToEmbeddedContextsQuery() {
        return this.insertComponentToEmbeddedContextsQuery;
    }

    private String constructInsertComponentToEmbeddedContextsQuery() {
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
        sql.append("    EMBEDDED_COMPONENT_ID ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    EMBEDDED_CONTEXTS_ID_SEQ.NEXTVAL, ");
        sql.append("    ? ,");
        sql.append("    2 ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    1 ,");
        sql.append("    ?  ");
        sql.append("  )");

        return sql.toString();
    }
}
