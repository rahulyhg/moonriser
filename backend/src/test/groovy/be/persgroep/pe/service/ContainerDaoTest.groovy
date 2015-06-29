package be.persgroep.pe.service

import be.persgroep.pe.service.data.TestDatabaseConfiguration
import be.persgroep.pe.service.data.dao.ContainerDao
import be.persgroep.pe.service.domain.container.Container
import be.persgroep.pe.service.domain.component.ContainerType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by gheylen on 4/12/2014.
 */
@ContextConfiguration(classes = [TestDatabaseConfiguration.class])
class ContainerDaoTest extends Specification {
    public static final COMPONENT_ID_UNDER_TEST = 2135055;

    @Autowired
    ContainerDao containerDao;

    def "Container insertion"() {
        when:
        final Container container = getBasicValidContainer();
        final int nextPossibleRank = containerDao.getNextContainerRankForComponentId(COMPONENT_ID_UNDER_TEST);

        then:
        containerDao.insertContainerDetails(container, nextPossibleRank, COMPONENT_ID_UNDER_TEST)
    }

    def "Container updating"() {
        when:
        final Container container = getBasicValidContainer()
        final int nextPossibleRank = containerDao.getNextContainerRankForComponentId(COMPONENT_ID_UNDER_TEST)
        containerDao.insertContainerDetails(container, nextPossibleRank, COMPONENT_ID_UNDER_TEST)
        container.setParagraphText("UPDATED TEST PARAGRAPH FROM TEST")

        then:
        containerDao.updateContainerDetails(container, nextPossibleRank)
    }

    def "Get next container ranking for component"() {
        when:
        final Container container = getBasicValidContainer()
        final int nextPossibleRankN = containerDao.getNextContainerRankForComponentId(COMPONENT_ID_UNDER_TEST)
        containerDao.insertContainerDetails(container, nextPossibleRankN, COMPONENT_ID_UNDER_TEST)
        final int nextPossibleRankNPlusOne = containerDao.getNextContainerRankForComponentId(COMPONENT_ID_UNDER_TEST);

        then:
        (nextPossibleRankN + 1) == nextPossibleRankNPlusOne
    }

    def "Insert invalid container"() {
        when:
        final Container container = this.getBasicInvalidContainerTypeNull();
        final int nextPossibleRank = containerDao.getNextContainerRankForComponentId(COMPONENT_ID_UNDER_TEST);
        containerDao.insertContainerDetails(container, nextPossibleRank, COMPONENT_ID_UNDER_TEST)

        then:
        thrown(Exception)
    }

    private Container getBasicValidContainer() {
        final Container basicValidContainer = new Container();

        basicValidContainer.setParagraphText("TEST PARAGRAPH FROM TEST")
        basicValidContainer.setTitle("TEST TITLE FROM TEST")
        basicValidContainer.setType(ContainerType.MEDIA);

        return basicValidContainer;
    }

    private Container getBasicInvalidContainerTypeNull() {
        final Container basicInvalidContainerTypeNull = new Container();

        basicInvalidContainerTypeNull.setParagraphText("TEST PARAGRAPH FROM TEST")
        basicInvalidContainerTypeNull.setTitle("TEST TITLE FROM TEST")
        basicInvalidContainerTypeNull.setType(null);

        return basicInvalidContainerTypeNull;
    }
}
