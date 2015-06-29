package be.persgroep.pe.service.data.queries.embeddable;

// NOT YET IMPLEMENTED COMPLETELY;
@Deprecated()
public class AttachmentQueries {
    private final String insertAttachmentDetailsToAssetsQuery;
    private final String insertAssetToEmbeddedContextsQuery;

    private static volatile AttachmentQueries INSTANCE = null;

    private AttachmentQueries() {
        this.insertAttachmentDetailsToAssetsQuery = this.constructInsertAttachmentDetailsToAssetsQuery();
        this.insertAssetToEmbeddedContextsQuery = this.constructInsertAssetToEmbeddedContextsQuery();
    }

    public static AttachmentQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (AttachmentQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AttachmentQueries();
                }
            }
        }

        return INSTANCE;
    }

    public String getInsertAttachmentDetailsToAssets() {
        return this.insertAttachmentDetailsToAssetsQuery;
    }

    public String getInsertAtachmentToEmbeddedContextsQuery() {
        return this.insertAssetToEmbeddedContextsQuery;
    }

    private String constructInsertAttachmentDetailsToAssetsQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_ASSETS");
        sql.append("  ( ");
        sql.append("    ID ");
        sql.append(" ,  MIME_TYPE ");
        sql.append(" ,  ORIGINAL_FILE_NAME ");
        sql.append(" ,  ORIGINAL_FILE_EXTENSION ");
        sql.append(" ,  BYTE_SIZE ");
        sql.append(" ,  CREATION_USER_ID ");
        sql.append(" ,  CREATION_DATE ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    TO_DATE(? , 'yyyy/mm/dd hh24:mi:ss') ");
        sql.append("  )");

        return sql.toString();
    }

    private String constructInsertAssetToEmbeddedContextsQuery() {
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
        sql.append("    EMBEDDED_ASSET_ID ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    ? ,"); // ID is primary key (sequence) : configurable
        sql.append("    ? ,"); // EMBED_CONTAINER_ID is foreign key pointing to PE_EMBED_CONTAINERS : configurable
        sql.append("    4 ,"); // EMBEDDED_OBJECT_TYPE_ID : 4 = attachment |asset
        sql.append("    ? ,"); // EMBED_ZONE_TYPE_ID (configurable) is foreign key pointing to PE_EMBED_ZONE_TYPES
        sql.append("    ? ,"); // RANKING is configurable
        sql.append("    1 ,"); // EMBED_CTX_DISPLAY_TYPE_ID
        sql.append("    ?  "); // EMBEDDED_ASSET_ID (sequence) is foreign key pointing to PE_ASSETS
        sql.append("  )");

        return sql.toString();
    }
}
