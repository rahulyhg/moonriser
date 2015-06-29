package be.persgroep.pe.service.data.queries;

import java.util.Set;

public class NavigationQueries {
    private final String hasNavigationMoonriserSectionQuery;
    private final String insertLinkNavigationToSectionQuery;
    private final String insertLinkComponentToPublicationItemQuery;
    private final String selectAllPubItemsForNavigationsQuery;
    private final String deleteAllPublicationItemsLinksForComponentQuery;
    private final String isComponentLinkedToPublicationItemQuery;

    private static volatile NavigationQueries INSTANCE = null;

    private NavigationQueries() {
        this.hasNavigationMoonriserSectionQuery = this.constructHasNavigationMoonriserSectionQuery();
        this.insertLinkNavigationToSectionQuery = this.constructInsertLinkNavigationToSectionQuery();
        this.insertLinkComponentToPublicationItemQuery = this.constructInsertLinkComponentToPublicationItemQuery();
        this.selectAllPubItemsForNavigationsQuery = this.constructSelectAllPubItemsForNavigationsQuery();
        this.deleteAllPublicationItemsLinksForComponentQuery = this.constructDeleteAllPublicationItemsLinksForComponentQuery();
        this.isComponentLinkedToPublicationItemQuery = this.constructIsComponentLinkedToPublicationItemQuery();
    }

    public static NavigationQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (NavigationQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NavigationQueries();
                }
            }
        }

        return INSTANCE;
    }

    public String getHasNavigationMoonriserSection() {
        return this.hasNavigationMoonriserSectionQuery;
    }

    public String getInsertLinkNavigationToSection() {
        return this.insertLinkNavigationToSectionQuery;
    }

    public String getInsertLinkComponentToPublicationItem() {
        return this.insertLinkComponentToPublicationItemQuery;
    }

    public String getSelectAllPubItemsForNavigations(final Set<Integer> navigationIds) {
        return this.selectAllPubItemsForNavigationsQuery + QueryUtils.getInstance().convertSetToInClause(navigationIds);
    }

    public String getDeleteAllPublicationItemsLinksForComponent() {
        return this.deleteAllPublicationItemsLinksForComponentQuery;
    }

    public String getIsComponentLinkedToPublicationItem() {
        return this.isComponentLinkedToPublicationItemQuery;
    }

    private String constructHasNavigationMoonriserSectionQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("SELECT ID ");
        sql.append("FROM PE_PUBLICATION_ITEMS ");
        sql.append("WHERE SECTION_ID       =  ? ");
        sql.append("AND NAVIGATION_ITEM_ID =  ? ");

        return sql.toString();
    }

    private final String constructInsertLinkNavigationToSectionQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_PUBLICATION_ITEMS ");
        sql.append("  ( ");
        sql.append("    ID, ");
        sql.append("    SECTION_ID, ");
        sql.append("    NAVIGATION_ITEM_ID, ");
        sql.append("    PUBLICATION_ID ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    PE_PUBL_ITEMS_ID_SEQ.NEXTVAL, ");
        sql.append("    ? ,");
        sql.append("    ? ,");
        sql.append("    (SELECT PUBLICATION_ID FROM PE_NAVIGATION_ITEMS WHERE ID = ? ) ");
        sql.append("  ) ");

        return sql.toString();
    }

    private String constructInsertLinkComponentToPublicationItemQuery() {
        final StringBuilder sql;

        sql = new StringBuilder("INSERT ");
        sql.append("INTO PE_COMP_MAP_PUBL_ITEMS ");
        sql.append("  ( ");
        sql.append("    ID, ");
        sql.append("    PUBLICATION_ITEM_ID, ");
        sql.append("    COMPONENT_ID, ");
        sql.append("    PRIORITY, ");
        sql.append("    SELECTED, ");
        sql.append("    MASTER ");
        sql.append("  ) ");
        sql.append("  VALUES ");
        sql.append("  ( ");
        sql.append("    PE_COMP_MAP_PUBL_ITEMS_ID_SEQ.NEXTVAL, ");
        sql.append("    ? , ");
        sql.append("    ? , ");
        sql.append("    7, ");
        sql.append("    1, ");
        sql.append("    1 ");
        sql.append("  )");

        return sql.toString();
    }

    private String constructSelectAllPubItemsForNavigationsQuery() {
        return "SELECT ID FROM PE_PUBLICATION_ITEMS WHERE NAVIGATION_ITEM_ID IN ";
    }

    private String constructDeleteAllPublicationItemsLinksForComponentQuery() {
        return "DELETE FROM PE_COMP_MAP_PUBL_ITEMS WHERE COMPONENT_ID = ? ";
    }

    private String constructIsComponentLinkedToPublicationItemQuery() {
        return "SELECT ID FROM PE_COMP_MAP_PUBL_ITEMS WHERE COMPONENT_ID = ? AND PUBLICATION_ITEM_ID = ?";
    }
}
