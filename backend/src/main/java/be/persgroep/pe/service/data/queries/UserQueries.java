package be.persgroep.pe.service.data.queries;

public class UserQueries {
    private final String selectUserIdByAuthorIdQuery;

    private static volatile UserQueries INSTANCE = null;

    private UserQueries() {
        this.selectUserIdByAuthorIdQuery = this.constructSelectUserIdByAuthorIdQuery();
    }

    public static UserQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (UserQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserQueries();
                }
            }
        }

        return INSTANCE;
    }

    public String getSelectUserIdByAuthorId() {
        return this.selectUserIdByAuthorIdQuery;
    }

    private String constructSelectUserIdByAuthorIdQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("SELECT u.id ");
        sql.append("FROM PE_USERS u, ");
        sql.append("  PE_AUTHORS a, ");
        sql.append("  PE_USER_MAP_AUTHOR ua ");
        sql.append("WHERE ua.AUTHOR_ID = a.ID ");
        sql.append("AND ua.USER_ID     = u.ID ");
        sql.append("AND a.ID           = ? ");

        return sql.toString();
    }
}
