package be.persgroep.pe.service.data.queries.component;

public class ComponentQueries {
    private final String archiveComponentQuery;
    private final String existsComponentQuery;

    private static volatile ComponentQueries INSTANCE = null;

    protected ComponentQueries() {
        this.archiveComponentQuery = this.constructArchiveComponentQuery();
        this.existsComponentQuery = this.constructExistsComponentQuery();
    }

    public static ComponentQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (ComponentQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ComponentQueries();
                }
            }
        }

        return INSTANCE;
    }

    public String getArchiveComponent() {
        return this.archiveComponentQuery;
    }

    public String getExistsComponent() {
        return this.existsComponentQuery;
    }


    private String constructArchiveComponentQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("UPDATE ");
        sql.append("PE_COMPONENTS ");
        sql.append("  SET STATE = 5 ");
        sql.append("WHERE ID = ?");

        return sql.toString();
    }

    private String constructExistsComponentQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("SELECT ID ");
        sql.append("FROM PE_COMPONENTS ");
        sql.append("WHERE ID = ?");

        return sql.toString();
    }

}
