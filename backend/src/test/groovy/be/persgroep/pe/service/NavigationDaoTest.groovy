package be.persgroep.pe.service

import be.persgroep.pe.service.data.TestDatabaseConfiguration
import be.persgroep.pe.service.data.dao.NavigationDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

/**
 * Created by gheylen on 7/01/2015.
 */
@ContextConfiguration(classes = [TestDatabaseConfiguration.class])
@TestPropertySource("classpath:config/test.properties")
class NavigationDaoTest extends Specification {
    private final Integer NAVIGATION_WITH_MOONRISER_SECTION = 9090;
    private final Integer DEFAULT_NAVIGATION_ID = 9090;

    @Autowired
    NavigationDao navigationDao;

    def "Check has navigation moonriser section"() {
        when:
        final boolean hasNavigationMoonriserSection =
                this.navigationDao.hasNavigationMoonriserSection(NAVIGATION_WITH_MOONRISER_SECTION);
        then:
        hasNavigationMoonriserSection == true
    }

    def "Get all publicationitem IDs for navigation ID"() {
        when:
        final Set<Integer> navigationIds = new HashSet<>();
        navigationIds.add(DEFAULT_NAVIGATION_ID);

        final List<Integer> publicationItemIds = this.navigationDao.getAllPublicationItemIdsFor(navigationIds);

        then:
        publicationItemIds.size() != 0
    }

    def "Delete and add link component to publicationitem"() {
        when:
        final Set<Integer> navigationIds = new HashSet<>();
        navigationIds.add(DEFAULT_NAVIGATION_ID);

        final List<Integer> publicationItemIds = this.navigationDao.getAllPublicationItemIdsFor(navigationIds);

        this.navigationDao.deleteAllPublicationItemsLinksForComponent(ContainerDaoTest.COMPONENT_ID_UNDER_TEST);
        this.navigationDao.linkComponentToPubItem(publicationItemIds.get(0), ContainerDaoTest.COMPONENT_ID_UNDER_TEST);

        then:
        this.navigationDao.isComponentLinkedToPublicationItem(ContainerDaoTest.COMPONENT_ID_UNDER_TEST, publicationItemIds.get(0)) == true
    }

}