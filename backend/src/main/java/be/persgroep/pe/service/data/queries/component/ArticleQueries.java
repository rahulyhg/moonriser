package be.persgroep.pe.service.data.queries.component;

public class ArticleQueries extends ComponentQueries {
    private final String insertArticleDetailsQuery;
    private final String insertComponentDetailsQuery;

    private static volatile ArticleQueries INSTANCE = null;

    private ArticleQueries() {
        this.insertArticleDetailsQuery = this.constructInsertArticleDetailsQuery();
        this.insertComponentDetailsQuery = this.constructInsertComponentDetailsQuery();
    }

    public static ArticleQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (ArticleQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ArticleQueries();
                }
            }
        }

        return INSTANCE;
    }

    public String getInsertArticleDetails() {
        return this.insertArticleDetailsQuery;
    }

    public String getInsertComponentDetails() {
        return this.insertComponentDetailsQuery;
    }

    private String constructInsertArticleDetailsQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_ARTICLES ");
        sql.append("  ( ");
        sql.append("    ID, ");
        sql.append("    TITLE, ");
        sql.append("    INTRODUCTION, ");
        sql.append("    FREE_TEXT_LABEL, ");
        sql.append("    TYPE_ID, ");
        sql.append("    LOCALE, ");
        sql.append("    COMPONENT_ID, ");
        sql.append("    CREATION_USER_ID, ");
        sql.append("    CREATION_DATE, ");
        sql.append("    SUBTITLE, ");
        sql.append("    LABEL_TYPE_ID, ");
        sql.append("    CULTURE_REF_ID, ");
        sql.append("    CULTURE_TYPE_ID, ");
        sql.append("    STAR_RATING, ");
        sql.append("    REGIONAL, ");
        sql.append("    PRINT_PAGE_NUMBER, ");
        sql.append("    PRINT_TYPE, ");
        sql.append("    ACCEPT_CONTENT_UPDATES ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    ARTICLES_ID_SEQ.NEXTVAL, ");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    TO_DATE(? , 'yyyy/mm/dd hh24:mi:ss'), ");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    0 ");
        sql.append("  )");

        return sql.toString();
    }

    private String constructInsertComponentDetailsQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_COMPONENTS");
        sql.append("  ( ");
        sql.append("    ID, ");
        sql.append("    PUBLICATION_START_DATE, ");
        sql.append("    PUBLICATION_END_DATE, ");
        sql.append("    TYPE_ID, ");
        sql.append("    SOURCE_ID, ");
        sql.append("    REACTION_STATE, ");
        sql.append("    STATE, ");
        sql.append("    CREATION_DATE, ");
        sql.append("    CREATION_USER_ID, ");
        sql.append("    ORIGINATING_KEY, ");
        sql.append("    MODIFICATION_DATE, ");
        sql.append("    MODIFICATION_USER_ID, ");
        sql.append("    REACTION_END_DATE, ");
        sql.append("    RATING_STATE, ");
        sql.append("    EMO_VOTES_STATE, ");
        sql.append("    SOURCE_TEXT, ");
        sql.append("    IS_PAID_CONTENT, ");
        sql.append("    COMMENT_COLLECTION_IDS, ");
        sql.append("    PLUS_CONTENT, ");
        sql.append("    AUTHORS, ");
        sql.append("    MASTER_NODE_ID ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    ? ,");
        sql.append("    TO_DATE(? , 'yyyy/mm/dd hh24:mi:ss'), ");
        sql.append("    TO_DATE(? , 'yyyy/mm/dd hh24:mi:ss'), ");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    TO_DATE(? , 'yyyy/mm/dd hh24:mi:ss'), ");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    TO_DATE(? , 'yyyy/mm/dd hh24:mi:ss'), ");
        sql.append("    ? ,");
        sql.append("    TO_DATE(? , 'yyyy/mm/dd hh24:mi:ss'), ");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ?, ");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    ?");
        sql.append("  )");

        return sql.toString();
    }

}
