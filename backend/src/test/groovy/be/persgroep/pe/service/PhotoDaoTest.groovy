package be.persgroep.pe.service

import be.persgroep.pe.service.config.extservice.DioContentConfiguration
import be.persgroep.pe.service.data.dao.embeddable.PhotoDao
import be.persgroep.pe.service.domain.embeddable.photo.Photo
import be.persgroep.pe.service.extservice.DioContentStub
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

/**
 * Created by wim van den brande on 12/01/2015.
 */
@ContextConfiguration(classes = [DioContentConfiguration.class, DioContentStub.class])
@TestPropertySource("classpath:config/dio.properties")
class PhotoDaoTest extends Specification {
    private final static ASSET_ID_UNDER_TEST = 2519398;

    @Autowired
    private DioContentStub dioContentStub;

    @Autowired
    private PhotoDao photoDao

    def "Test Photo Dao"() {
        when:
        final Photo photo = new Photo();
        photo.setDioContentAssetId(ASSET_ID_UNDER_TEST);
        this.dioContentStub.getPhotoInfoByAssetId(photo);
        this.dioContentStub.saveAssetAttachmentToLocation(ASSET_ID_UNDER_TEST, photoDao.getFileLocationFor(photo, "base"))

        then:
        System.out.println("caption" + photo.getCaption());
    }
}