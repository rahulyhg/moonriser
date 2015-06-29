package be.persgroep.pe.service.data.dao;

import be.persgroep.pe.service.data.queries.NavigationQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Primary
@Repository
public class NavigationDao {
    @Autowired
    private Environment env;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean hasNavigationMoonriserSection(final int navigationId) {
        final List<Integer> result =
                this.jdbcTemplate.queryForList(
                        NavigationQueries.getInstance().getHasNavigationMoonriserSection(),
                        Integer.class,
                        Integer.parseInt(this.env.getProperty("pe.section.moonriserId")),
                        navigationId);

        if (result.size() == 0) {
            return false;
        }

        return true;
    }

    public void addNavigationToMoonriserSection(final int navigationId) {
        this.jdbcTemplate.update(
                NavigationQueries.getInstance().getInsertLinkNavigationToSection(),
                Integer.parseInt(this.env.getProperty("pe.section.moonriserId")),
                navigationId);
    }

    public List<Integer> getAllPublicationItemIdsFor(final Set<Integer> navigationIds) {
        return this.jdbcTemplate.queryForList(
                NavigationQueries.getInstance().getSelectAllPubItemsForNavigations(navigationIds),
                Integer.class);
    }

    public void linkComponentToPubItem(final int publicationItemId, final int componentId) {
        this.jdbcTemplate.update(
                NavigationQueries.getInstance().getInsertLinkComponentToPublicationItem(),
                publicationItemId,
                componentId);
    }

    public void deleteAllPublicationItemsLinksForComponent(final int componentId) {
        this.jdbcTemplate.update(
                NavigationQueries.getInstance().getDeleteAllPublicationItemsLinksForComponent(),
                componentId);
    }

    public boolean isComponentLinkedToPublicationItem(final int componentId, final int publicationItemId) {
        final List<Integer> result =
                this.jdbcTemplate.queryForList(
                        NavigationQueries.getInstance().getIsComponentLinkedToPublicationItem(),
                        Integer.class,
                        componentId,
                        publicationItemId);

        if (result.size() == 0) {
            return false;
        }

        return true;
    }
}
