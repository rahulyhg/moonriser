package be.persgroep.pe.service.data.dao.embeddable;

import be.persgroep.pe.service.data.queries.PhotoQueries;
import be.persgroep.pe.service.data.queries.SequenceQueries;
import be.persgroep.pe.service.domain.embeddable.photo.Photo;
import be.persgroep.pe.service.domain.embeddable.photo.PhotoCroppingType;
import be.persgroep.pe.service.extservice.DioContentStub;
import be.persgroep.pe.service.service.chain.SaveArticleChain;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.io.File;
import java.io.IOException;

@Primary
@Repository
public class PhotoDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private Environment env;
    @Autowired
    private DioContentStub dioContent;

    private PhotoPathFactory photoPathFactory;

    public PhotoDao() {
        this.photoPathFactory = this.new PhotoPathFactory();
    }

    // first fill in the details of the photo by retrieving data from the external DioContent system
    // afterwards, write photo to flat file
    //      the transactionListener (once registered) will validate if the transaction (as annotated as @Transactional)
    //      succeeded or failed
    //      if the transaction failed, the afterCompletion method will be responsible for deleting the file
    // afterwards (in case of success), persists the photo in DBMS

    public void insertPhoto(final Photo photo, final int containerId) throws IOException {
        this.fillInPhotoId(photo);
        this.dioContent.fillInPhotoInfo(photo);
        this.savePhotoBinaryFromDioContentToFileSystem(photo);
        this.insertPhotoInDatabase(photo, containerId);
    }

    private void fillInPhotoId(final Photo photo) {
        photo.setId(this.jdbcTemplate.queryForObject(SequenceQueries.getInstance().getNextPhotoId(), Integer.class));
    }

    private void savePhotoBinaryFromDioContentToFileSystem(Photo photo) throws IOException {
        SaveArticleChain transactionListener = new SaveArticleChain(this.photoPathFactory.buildPathFor(photo, PhotoCroppingType.BASE));
        TransactionSynchronizationManager.registerSynchronization(transactionListener);

        //TODO:PEPR-1546 add conversion for multiple PE photo formats
        dioContent.saveAssetAttachmentToLocation(photo.getDioContentAssetId(), this.photoPathFactory.buildPathFor(photo, PhotoCroppingType.BASE));
        dioContent.saveAssetAttachmentToLocation(photo.getDioContentAssetId(), this.photoPathFactory.buildPathFor(photo, PhotoCroppingType.MEDIA_L));
        dioContent.saveAssetAttachmentToLocation(photo.getDioContentAssetId(), this.photoPathFactory.buildPathFor(photo, PhotoCroppingType.MEDIA_XL));
        //TODO: Photo crop + resize
    }

    private void insertPhotoInDatabase(final Photo photo, final int containerId) {
        this.jdbcTemplate.update(
                PhotoQueries.getInstance().getInsertPhotoQuery(),
                photo.getId(),
                photo.getMimeType().getName(),
                photo.getWidth(),
                photo.getHeight(),
                photo.getOriginalCreationDate().toString("yyyy/MM/dd HH:mm:ss"),
                photo.getDioContentAssetId(),
                photo.getCaption(),
                photo.getCredit()
        );

        this.jdbcTemplate.update(
                PhotoQueries.getInstance().getInsertPhotoContextQuery(),
                photo.getId());

        this.jdbcTemplate.update(
                PhotoQueries.getInstance().getInsertTableToEmbeddedContextsQuery(),
                containerId,
                photo.getAlignment().getId(),
                photo.getRanking(),
                photo.getId());
    }

    class PhotoPathFactory {
        public File buildPathFor(final Photo photo, final PhotoCroppingType photoCroppingType) {
            final StringBuilder stringBuilder;

            stringBuilder = new StringBuilder(env.getProperty("pe.photo.location"));
            stringBuilder.append(DateTime.now().get(DateTimeFieldType.year()));
            stringBuilder.append("/");

            for (int modulo : new int[]{19, 17, 15}) {
                stringBuilder.append(photo.getId() % modulo).append("/");
            }

            stringBuilder.append(photoCroppingType.name().toLowerCase());
            stringBuilder.append("_");
            stringBuilder.append(photo.getId());
            stringBuilder.append(".");
            stringBuilder.append(photo.getMimeType().getExtension());

            return new File(stringBuilder.toString());
        }
    }
}
