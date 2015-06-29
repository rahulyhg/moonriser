package be.persgroep.pe.service.extservice

import be.persgroep.pe.service.config.extservice.DioContentConfiguration
import be.persgroep.pe.service.data.TestDatabaseConfiguration
import be.persgroep.pe.service.data.dao.embeddable.PhotoDao
import be.persgroep.pe.service.domain.embeddable.photo.Photo
import be.persgroep.pe.service.domain.embeddable.EmbeddableAlignment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

/**
 * Created by wim van den brande on 12/01/2015.
 */
@ContextConfiguration(classes = [DioContentConfiguration.class, DioContentStub.class, TestDatabaseConfiguration.class])
@TestPropertySource(["classpath:config/dio.properties", "classpath:config/test.properties"])
class DioContentStubTest extends Specification {
    private final static ASSET_ID_UNDER_TEST = 2519398;

    @Autowired
    private DioContentStub dioContentStub;

    def "Test dio content access"() {
        when:
        final Photo photo = new Photo();
        photo.setDioContentAssetId(ASSET_ID_UNDER_TEST);
        this.dioContentStub.fillInPhotoInfo(photo);

        then:
        photo.getCaption().equals("24-3-2014 AMSTERDAM - President Barak Obama with Mark Rutte visits the Rijksmuseum in Amsterdam and stands for the  Nachtwacht the world famous painting of painter Rembrandt COPYRIGHT ROBIN UTRECHT")
        photo.getMimeType().getName().equals("image/jpeg")
    }

}