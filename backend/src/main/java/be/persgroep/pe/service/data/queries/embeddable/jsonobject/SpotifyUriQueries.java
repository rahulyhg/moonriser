package be.persgroep.pe.service.data.queries.embeddable.jsonobject;

public class SpotifyUriQueries extends JsonObjectQueries {
    private final String insertSpotifyDetailsToJsonObjectQuery;

    private static volatile SpotifyUriQueries INSTANCE = null;

    private SpotifyUriQueries() {
        this.insertSpotifyDetailsToJsonObjectQuery = this.constructInsertSpotifyDetailsToJsonObjectQuery();
    }

    public static SpotifyUriQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (SpotifyUriQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SpotifyUriQueries();
                }
            }
        }

        return INSTANCE;
    }

    private String constructInsertSpotifyDetailsToJsonObjectQuery() {
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
        sql.append("    4 ,");
        sql.append("    ?  ");
        sql.append("  )");

        return sql.toString();
    }

    public String getInsertSpotifyDetailsToJsonObjectQuery() {
        return this.insertSpotifyDetailsToJsonObjectQuery;
    }
}
