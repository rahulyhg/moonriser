package be.persgroep.pe.service.data.queries;

public class PhotoQueries {
    private final String insertPhotoQuery;
    private final String insertPhotoContextQuery;
    private final String insertTableToEmbeddedContextsQuery;

    private static volatile PhotoQueries INSTANCE = null;

    private PhotoQueries() {
        this.insertPhotoQuery = this.constructInsertPhotoQuery();
        this.insertPhotoContextQuery = this.constructInsertPhotoContextQuery();
        this.insertTableToEmbeddedContextsQuery = this.constructInsertTableToEmbeddedContextsQuery();
    }

    public static PhotoQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (PhotoQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PhotoQueries();
                }
            }
        }

        return INSTANCE;
    }

    public String getInsertPhotoQuery() {
        return this.insertPhotoQuery;
    }

    public String getInsertPhotoContextQuery() {
        return insertPhotoContextQuery;
    }

    public String getInsertTableToEmbeddedContextsQuery() {
        return insertTableToEmbeddedContextsQuery;
    }

    private String constructInsertPhotoQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_PHOTOS ");
        sql.append("  ( ");
        sql.append("    ID, ");
        sql.append("    MIME_TYPE, ");
        sql.append("    WIDTH, ");
        sql.append("    HEIGHT, ");
        sql.append("    ORIGINAL_CREATION_DATE, ");
        sql.append("    SOURCE_TYPE_ID, ");
        sql.append("    SOURCE_KEY, ");
        sql.append("    CREATION_DATE, ");
        sql.append("    MODIFICATION_DATE, ");
        sql.append("    CREDIT_TYPE_ID, ");
        sql.append("    ORIGINAL_CAPTION, ");
        sql.append("    CREDIT_TEXT ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    TO_DATE(? , 'yyyy/mm/dd hh24:mi:ss') ,");
        sql.append("    5 ,"); // SOURCE_TYPE_ID 5 = dio content
        sql.append("    ? ,");
        sql.append("    SYSDATE ,");
        sql.append("    SYSDATE ,");
        sql.append("    0 ,");
        sql.append("    ? ,");
        sql.append("    ? ");
        sql.append("  )");

        return sql.toString();
    }

    private String constructInsertPhotoContextQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_PHOTO_CONTEXTS ");
        sql.append("  ( ");
        sql.append("    ID, ");
        sql.append("    PHOTO_ID, ");
        sql.append("    UPLOAD_DATE ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    PHOTO_CONTEXTS_ID_SEQ.NEXTVAL, ");
        sql.append("    ? ,");
        sql.append("    SYSDATE");
        sql.append("  )");

        return sql.toString();
    }

    private String constructInsertTableToEmbeddedContextsQuery() {
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
        sql.append("    EMBEDDED_PHOTO_ID ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    EMBEDDED_CONTEXTS_ID_SEQ.NEXTVAL ,");
        sql.append("    ? ,");
        sql.append("    1 ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    1 ,");
        sql.append("    ?  ");
        sql.append("  )");

        return sql.toString();
    }

}
