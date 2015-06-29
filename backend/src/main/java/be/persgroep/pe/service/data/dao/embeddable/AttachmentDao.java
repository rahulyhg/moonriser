package be.persgroep.pe.service.data.dao.embeddable;

import be.persgroep.pe.service.data.queries.SequenceQueries;
import be.persgroep.pe.service.data.queries.embeddable.AttachmentQueries;
import be.persgroep.pe.service.domain.embeddable.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class AttachmentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // NOT YET IMPLEMENTED COMPLETELY;
    @Deprecated()
    public int insertAttachment(final Attachment attachment) {
        final Integer attachmentId = jdbcTemplate.queryForObject(SequenceQueries.getInstance().getNextPeAssetRowId(), Integer.class);

        jdbcTemplate.update(
                AttachmentQueries.getInstance().getInsertAttachmentDetailsToAssets(),
                attachmentId,
                attachment.getAttachmentType().getAttachmentTypeId(),
                attachment.getFileName(),
                attachment.getFileExtension(),
                attachment.getFileSize(),
                attachment.getUserId(),
                attachment.getCreationDate().toString("yyyy/MM/dd HH:mm:ss"));

        return attachmentId;
    }
}
