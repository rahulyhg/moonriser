package be.persgroep.pe.service.data.dao;

import be.persgroep.pe.service.data.queries.ContainerQueries;
import be.persgroep.pe.service.data.queries.SequenceQueries;
import be.persgroep.pe.service.data.statement.*;
import be.persgroep.pe.service.domain.container.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class ContainerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getNextContainerRankForComponentId(final int componentId) {
        return this.jdbcTemplate.queryForObject(ContainerQueries.getInstance().getSelectNextContainerRankForComponentId(), Integer.class, componentId);
    }

    /**
     * Updating only supports changing: title, ranking, type, and paragraph text. Changing a container to another component isn't supported.
     *
     * @param container The container to be updated
     */
    public void updateContainerDetails(final Container container, final int ranking) {
        if (container.getId() == null) {
            throw new IllegalArgumentException("Given container should have an ID when getting updated.");
        }

        this.jdbcTemplate.update(ContainerQueries.getInstance().getUpdateContainerDetails(), new UpdateContainerDetailsSetter(container.getId(),
                container.getType().getId(), ranking,
                container.getTitle()));

        this.jdbcTemplate.update(ContainerQueries.getInstance().getUpdateContainerText(), new UpdateContainerTextSetter(container.getParagraphText(),
                container.getId()));
    }

    /**
     * @param container   The container to be saved
     * @param componentId The component to which it should be linked
     * @return The ID of the inserted or updated container
     */
    public int insertContainerDetails(final Container container, final int ranking, final int componentId) {
        final int containerId = this.jdbcTemplate.queryForObject(SequenceQueries.getInstance().getNextEmbeddedContainerId(), Integer.class);
        this.jdbcTemplate.update(ContainerQueries.getInstance().getInsertContainerDetails(), new InsertContainerDetailsSetter(containerId, componentId, container.getType().getId(), ranking, container.getTitle()));

        final int paragraphId = this.jdbcTemplate.queryForObject(SequenceQueries.getInstance().getNextPeParagraphsId(), Integer.class);
        this.jdbcTemplate.update(ContainerQueries.getInstance().getInsertContainerTextToPeParagraphs(), new SimpleIdStatementSetter(paragraphId));

        final int textId = this.jdbcTemplate.queryForObject(SequenceQueries.getInstance().getNextPeTextsId(), Integer.class);
        this.jdbcTemplate.update(ContainerQueries.getInstance().getInsertContainerTextToPeTexts(), new InsertContainerTextToPeTextsSetter(textId, container.getParagraphText(), paragraphId));

        final int embeddedContextId = this.jdbcTemplate.queryForObject(SequenceQueries.getInstance().getNextPeEmbeddedContextId(), Integer.class);
        this.jdbcTemplate.update(ContainerQueries.getInstance().getInsertContainerTextToEmbeddedContexts(), new InsertContainerTextToEmbeddedContextsSetter(embeddedContextId, paragraphId, containerId));

        container.setId(containerId);
        return containerId;
    }

}
