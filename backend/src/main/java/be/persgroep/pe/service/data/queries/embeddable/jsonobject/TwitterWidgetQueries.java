package be.persgroep.pe.service.data.queries.embeddable.jsonobject;

public class TwitterWidgetQueries extends JsonObjectQueries {
    private final String insertTwitterWidgetDetailsToJsonObjectQuery;

    private static volatile TwitterWidgetQueries INSTANCE = null;

    private TwitterWidgetQueries() {
        this.insertTwitterWidgetDetailsToJsonObjectQuery = this.constructInsertTwitterWidgetDetailsToJsonObjectQuery();
    }

    public static TwitterWidgetQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (TwitterWidgetQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TwitterWidgetQueries();
                }
            }
        }

        return INSTANCE;
    }

    private String constructInsertTwitterWidgetDetailsToJsonObjectQuery() {
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
        sql.append("    3 ,");
        sql.append("    ?  ");
        sql.append("  )");

        return sql.toString();
    }

    public String getInsertTwitterWidgetDetailsToJsonObjectQuery() {
        return this.insertTwitterWidgetDetailsToJsonObjectQuery;
    }
}
