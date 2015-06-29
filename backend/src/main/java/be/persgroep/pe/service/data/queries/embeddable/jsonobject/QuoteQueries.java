package be.persgroep.pe.service.data.queries.embeddable.jsonobject;

public class QuoteQueries extends JsonObjectQueries {
    private final String insertQuoteDetailsToJsonObjectQuery;

    private static volatile QuoteQueries INSTANCE = null;

    private QuoteQueries() {
        this.insertQuoteDetailsToJsonObjectQuery = this.constructInsertQuoteDetailsToJsonObjectQuery();
    }

    public static QuoteQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (QuoteQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new QuoteQueries();
                }
            }
        }

        return INSTANCE;
    }

    private String constructInsertQuoteDetailsToJsonObjectQuery() {
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
        sql.append("    1 ,");
        sql.append("    ?  ");
        sql.append("  )");

        return sql.toString();
    }

    public String getInsertQuoteDetailsToJsonObjectQuery() {
        return this.insertQuoteDetailsToJsonObjectQuery;
    }
}
