package be.persgroep.pe.service.data.queries.embeddable.jsonobject;

public abstract class JsonObjectQueries {
    private final String selectValueFromJsonObjectQuery;
    private final String insertJsonObjectToEmbeddedContextsQuery;

    protected JsonObjectQueries() {
        this.selectValueFromJsonObjectQuery = this.constructSelectValueFromJsonObject();
        this.insertJsonObjectToEmbeddedContextsQuery = this.constructInsertJsonObjectToEmbeddedContextsQuery();
    }

    public String getInsertJsonObjectToEmbeddedContexts() {
        return this.insertJsonObjectToEmbeddedContextsQuery;
    }

    public String getGetSelectValueFromJsonObject() {
        return this.selectValueFromJsonObjectQuery;
    }

    private String constructInsertJsonObjectToEmbeddedContextsQuery() {
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
        sql.append("    EMBEDDED_JSON_OBJECT_ID ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    EMBEDDED_CONTEXTS_ID_SEQ.NEXTVAL ,");
        sql.append("    ? ,");
        sql.append("    9 ,"); // 9 = JSON
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    1 ,");
        sql.append("    ?  ");
        sql.append("  )");

        return sql.toString();
    }

    private String constructSelectValueFromJsonObject() {
        final StringBuilder sql;

        sql = new StringBuilder("SELECT ");
        sql.append("VALUE ");
        sql.append("FROM ");
        sql.append("PE_JSON_OBJECTS ");
        sql.append("WHERE   ID = ?");

        return sql.toString();
    }
}
