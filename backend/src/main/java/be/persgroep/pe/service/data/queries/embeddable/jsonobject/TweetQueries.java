package be.persgroep.pe.service.data.queries.embeddable.jsonobject;

public class TweetQueries extends JsonObjectQueries {
    private final String insertTwitterDetailsToJsonObjectQuery;

    private static volatile TweetQueries INSTANCE = null;

    private TweetQueries() {
        this.insertTwitterDetailsToJsonObjectQuery = this.constructInsertTwitterDetailsToJsonObjectQuery();
    }

    public static TweetQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (TweetQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TweetQueries();
                }
            }
        }

        return INSTANCE;
    }

    private String constructInsertTwitterDetailsToJsonObjectQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_JSON_OBJECTS");
        sql.append("  ( ");
        sql.append("    ID, ");
        sql.append("    TYPE_ID, ");
        sql.append("    VALUE ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    ? ,");
        sql.append("    2 ,");
        sql.append("    ?  ");
        sql.append("  )");

        return sql.toString();
    }

    public String getInsertTwitterDetailsToJsonObjectQuery() {
        return this.insertTwitterDetailsToJsonObjectQuery;
    }
}
