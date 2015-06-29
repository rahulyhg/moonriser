package be.persgroep.pe.service.data.queries;

public class ContainerQueries {
    private final String selectNextContainerRankForComponentIdQuery;
    private final String updateContainerDetailsQuery;
    private final String updateContainerTextQuery;
    private final String insertContainerTextToPeTextsQuery;
    private final String insertContainerTextToPeParagraphsQuery;
    private final String insertContainerTextToEmbeddedContextsQuery;
    private final String insertContainerDetailsQuery;

    private static volatile ContainerQueries instance = null;

    private ContainerQueries() {
        this.selectNextContainerRankForComponentIdQuery = this.constructSelectNextContainerRankForComponentIdQuery();
        this.updateContainerDetailsQuery = this.constructUpdateContainerDetailsQuery();
        this.updateContainerTextQuery = this.constructUpdateContainerText();
        this.insertContainerTextToPeTextsQuery = this.constructInsertContainerTextToPeTexts();
        this.insertContainerTextToPeParagraphsQuery = this.constructInsertContainerTextToPeParagraphsQuery();
        this.insertContainerTextToEmbeddedContextsQuery = this.constructInsertContainerTextToEmbeddedContextsQuery();
        this.insertContainerDetailsQuery = this.constructInsertContainerDetailsQuery();
    }

    public static ContainerQueries getInstance() {
        if (instance == null) {
            synchronized (ContainerQueries.class) {
                if (instance == null) {
                    instance = new ContainerQueries();
                }
            }
        }

        return instance;
    }


    public String getSelectNextContainerRankForComponentId() {
        return this.selectNextContainerRankForComponentIdQuery;
    }

    public String getUpdateContainerDetails() {
        return this.updateContainerDetailsQuery;
    }

    public String getUpdateContainerText() {
        return this.updateContainerTextQuery;
    }

    public String getInsertContainerTextToPeTexts() {
        return this.insertContainerTextToPeTextsQuery;
    }

    public String getInsertContainerTextToPeParagraphs() {
        return this.insertContainerTextToPeParagraphsQuery;
    }

    public String getInsertContainerTextToEmbeddedContexts() {
        return this.insertContainerTextToEmbeddedContextsQuery;
    }

    public String getInsertContainerDetails() {
        return this.insertContainerDetailsQuery;
    }

    private String constructSelectNextContainerRankForComponentIdQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("SELECT (MAX(RANKING) + 1) AS nextContainerId ");
        sql.append("FROM PE_EMBED_CONTAINERS ");
        sql.append("WHERE COMPONENT_ID = ?");

        return sql.toString();
    }

    private String constructUpdateContainerDetailsQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("UPDATE PE_EMBED_CONTAINERS ");
        sql.append("SET EMBED_CONTAINER_TYPE_ID = ? ,");
        sql.append("  RANKING                   = ? ,");
        sql.append("  TITLE                     = ? ");
        sql.append("WHERE ID                    = ? ");

        return sql.toString();
    }

    private String constructUpdateContainerText() {
        final StringBuilder sql;

        sql = new StringBuilder("UPDATE PE_TEXTS ");
        sql.append("SET TEXT             = ? ");
        sql.append("WHERE ( PARAGRAPH_ID = ");
        sql.append("  (SELECT EMBEDDED_PARAGRAPH_ID ");
        sql.append("  FROM PE_EMBEDDED_CONTEXTS ");
        sql.append("  WHERE EMBED_CONTAINER_ID      = ? ");
        sql.append("  AND EMBEDDED_OBJECT_TYPE_ID   = 5 ");
        sql.append("  AND EMBED_ZONE_TYPE_ID        = 4 ");
        sql.append("  AND EMBED_CTX_DISPLAY_TYPE_ID = 1 ");
        sql.append("  AND RANKING                   = 1 ");
        sql.append("  ) )");

        return sql.toString();
    }

    private String constructInsertContainerTextToPeTexts() {
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
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    1, ");
        sql.append("    ? ");
        sql.append("  )");

        return sql.toString();
    }

    private String constructInsertContainerTextToPeParagraphsQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT INTO PE_PARAGRAPHS ");
        sql.append("  ( ID )");
        sql.append("  VALUES ");
        sql.append("  ( ? )");

        return sql.toString();
    }

    private String constructInsertContainerTextToEmbeddedContextsQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_EMBEDDED_CONTEXTS ");
        sql.append("  ( ");
        sql.append("    ID, ");
        sql.append("    EMBEDDED_PARAGRAPH_ID, ");
        sql.append("    EMBED_CONTAINER_ID, ");
        sql.append("    EMBEDDED_OBJECT_TYPE_ID, ");
        sql.append("    EMBED_ZONE_TYPE_ID, ");
        sql.append("    RANKING, ");
        sql.append("    EMBED_CTX_DISPLAY_TYPE_ID ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    5, ");
        sql.append("    4, ");
        sql.append("    1, ");
        sql.append("    1 ");
        sql.append("  )");

        return sql.toString();
    }

    private String constructInsertContainerDetailsQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_EMBED_CONTAINERS ");
        sql.append("  ( ");
        sql.append("    ID, ");
        sql.append("    COMPONENT_ID, ");
        sql.append("    EMBED_CONTAINER_TYPE_ID, ");
        sql.append("    RANKING, ");
        sql.append("    TITLE ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ");
        sql.append("  )");

        return sql.toString();
    }
}
